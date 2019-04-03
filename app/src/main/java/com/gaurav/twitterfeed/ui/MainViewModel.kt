package com.gaurav.twitterfeed.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.twitter.sdk.android.core.TwitterSession

/**
 * Created by Gaurav on 02-04-2019.
 */
class MainViewModel : ViewModel() {

    val twitterSessionData = MutableLiveData<TwitterSession>()

}