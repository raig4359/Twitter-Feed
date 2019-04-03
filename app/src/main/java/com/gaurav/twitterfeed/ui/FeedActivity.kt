package com.gaurav.twitterfeed.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gaurav.twitterfeed.R

class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        RestClientOld.webServices.getFeeds().enqueue(object:Callback, retrofit2.Callback<ApiResponse<ArrayList<Any>>> {
//            override fun onFailure(call: Call<ApiResponse<ArrayList<Any>>>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<ApiResponse<ArrayList<Any>>>,
//                                    response: Response<ApiResponse<ArrayList<Any>>>) {
//
//            }
//
//        })
    }
}
