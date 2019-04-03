package com.gaurav.twitterfeed.network

import com.twitter.sdk.android.core.TwitterSession
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by Gaurav on 02-04-2019.
 */
object RestClient {

    private var twitterClient: CustomTwitterClient? = null

    fun twitterClient(twitterSession: TwitterSession): CustomTwitterClient?  {
        if (twitterClient == null) {
            twitterClient = CustomTwitterClient(twitterSession, getOkHttpClient())
        }
        return twitterClient
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(getHttpLoggingInterceptor())
            .addInterceptor(ConnectivityInterceptor())
            .build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}