package com.gaurav.twitterfeed.network

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.os.AsyncTask
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType?>>()

    fun getAsLiveData(): LiveData<Resource<ResultType?>> {
        return result
    }

    init {
        result.setValue(Resource.loading(null))
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data!!)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { resultType ->
                    result.setValue(Resource.success(resultType))
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { data ->
            result.setValue(Resource.loading(data))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            if (response!!.isSuccessful) {
                saveResultAndReInit(response)
            } else {
                onFetchFailed()
                result.addSource(dbSource) {
                    if (response.isInternetConnectionError) {
                        result.setValue(Resource.noInternetConnection(it))
                    } else {
                        result.setValue(Resource.error("", it))
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun saveResultAndReInit(response: ApiResponse<RequestType>) {
        object : AsyncTask<Void, Void, Void?>() {

            override fun doInBackground(vararg voids: Void): Void? {
                saveCallResult(response.body)
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                result.addSource(loadFromDb()) {
                    result.setValue(Resource.success(it))
                }
            }
        }.execute()
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)

    @MainThread
    protected abstract fun shouldFetch(item: ResultType): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @MainThread
    protected fun onFetchFailed() {

    }


}