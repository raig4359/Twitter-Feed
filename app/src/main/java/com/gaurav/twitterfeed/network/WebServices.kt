package com.gaurav.twitterfeed.network

import android.arch.lifecycle.LiveData
import com.gaurav.twitterfeed.model.TestD
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface WebServices {

    @GET("api")
    fun getTest(@QueryMap options: Map<String, Int>): LiveData<ApiResponse<ArrayList<TestD>>>

}