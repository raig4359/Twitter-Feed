package com.gaurav.twitterfeed.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.gaurav.twitterfeed.model.Tweet
import com.gaurav.twitterfeed.network.CustomTwitterClient
import com.gaurav.twitterfeed.network.Resource
import com.gaurav.twitterfeed.repository.TweetsRepository

class FeedsViewModel(private val tweetRepository: TweetsRepository) : ViewModel() {

    fun getFeeds(customApiClient: CustomTwitterClient?): LiveData<Resource<List<Tweet>?>> {
        return tweetRepository.getFeeds(customApiClient)
    }

    class Factory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

        private var tweetRepository: TweetsRepository = TweetsRepository(context)

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedsViewModel(tweetRepository) as T
        }

    }

}