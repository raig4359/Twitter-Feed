package com.gaurav.twitterfeed.network

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession
import okhttp3.OkHttpClient


/**
 * Created by Gaurav on 02-04-2019.
 */
class CustomTwitterClient(session: TwitterSession, okHttpClient: OkHttpClient) : TwitterApiClient(session, okHttpClient) {

    val webServices: WebServices
        get() = getService(WebServices::class.java)

}