package com.gaurav.twitterfeed.network

import android.content.Context
import com.gaurav.twitterfeed.TwitterFeedApp
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class ConnectivityInterceptor : Interceptor {

    private val mContext: Context = TwitterFeedApp.appContext!!

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain):  Response? {
        if (!NetworkUtil.isOnline(mContext)) {
            throw NoConnectivityException()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}