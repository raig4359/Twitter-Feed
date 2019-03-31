package com.gaurav.twitterfeed

import android.app.Application
import android.content.Context
import android.util.Log
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig


class TwitterFeedApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        setUpTwitter()
    }

    private fun setUpTwitter() {
        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.ERROR))
                .twitterAuthConfig(TwitterAuthConfig(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET))
                .debug(true)
                .build()
        Twitter.initialize(config)
    }

    companion object {
        var appContext: Context? = null
    }

}