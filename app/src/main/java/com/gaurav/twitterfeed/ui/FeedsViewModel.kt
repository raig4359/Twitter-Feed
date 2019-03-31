package com.gaurav.twitterfeed.ui

import android.arch.lifecycle.LiveData
import com.gaurav.twitterfeed.model.TestD
import com.gaurav.twitterfeed.network.ApiResponse
import com.gaurav.twitterfeed.network.NetworkBoundResource
import com.gaurav.twitterfeed.network.Resource
import com.gaurav.twitterfeed.network.RestClient

class FeedsViewModel {

    fun getTestDummy(params: HashMap<String, Int>): LiveData<Resource<ArrayList<TestD>?>> {
        return object : NetworkBoundResource<ArrayList<TestD>,ArrayList<TestD>>() {
            override fun saveCallResult(item: ArrayList<TestD>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(item: ArrayList<TestD>): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromDb(): LiveData<ArrayList<TestD>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<TestD>>> {
                return RestClient.webServices.getTest(params)
            }
        }.getAsLiveData()
    }

}