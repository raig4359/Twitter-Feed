package com.gaurav.twitterfeed.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.gaurav.twitterfeed.R
import com.gaurav.twitterfeed.SharedPrefUtils
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterAuthToken
import com.twitter.sdk.android.core.TwitterSession

private const val LOGIN_FRAGMENT = "LoginFragment"
private const val FEED_FRAGMENT = "FeedFragment"

class OnBoardingActivity : AppCompatActivity(), LoginFragment.LoginInteraction, FeedFragment.OnFeedInteractionListener {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        init()
    }

    private fun init() {
        if (SharedPrefUtils.isLoggedIn(applicationContext)) {
            val authToken = SharedPrefUtils.getAuthToken(applicationContext)
            val authSecret = SharedPrefUtils.getAuthSecret(applicationContext)
            val userId = SharedPrefUtils.getUserId(applicationContext)
            val userName = SharedPrefUtils.getUserName(applicationContext)
            val twitterSession = TwitterSession(TwitterAuthToken(authToken, authSecret), userId, userName)
            mainViewModel.twitterSessionData.value = twitterSession
            openFragment(FeedFragment.newInstance(), FEED_FRAGMENT)
        } else {
            openFragment(LoginFragment.newInstance(), LOGIN_FRAGMENT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val loginFragment = supportFragmentManager.findFragmentByTag(LOGIN_FRAGMENT)
        loginFragment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onLoginSuccessful(result: Result<TwitterSession>) {
        openFragment(FeedFragment.newInstance(), FEED_FRAGMENT)
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(tag, 0)
        if (!fragmentPopped && manager.findFragmentByTag(tag) == null) {
            manager.beginTransaction()
                .replace(R.id.frame, fragment, tag)
                .addToBackStack(tag)
                .commit()
        }
    }

    override fun onBackPressed() {
        finish()
    }

}
