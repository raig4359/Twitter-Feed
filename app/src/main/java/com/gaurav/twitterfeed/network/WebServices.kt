package com.gaurav.twitterfeed.network

import com.gaurav.twitterfeed.model.Tweet
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    @GET("/1.1/statuses/home_timeline.json")
    fun getFeeds(): Call<List<Tweet>>

}