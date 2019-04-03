package com.gaurav.twitterfeed.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gaurav.twitterfeed.R
import com.gaurav.twitterfeed.SharedPrefUtils
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private var listener: LoginInteraction? = null
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        login_button.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                SharedPrefUtils.setUserName(activity!!.applicationContext, result.data.userName)
                SharedPrefUtils.setUserId(activity!!.applicationContext, result.data.userId)
                SharedPrefUtils.setAuthToken(activity!!.applicationContext, result.data.authToken.token)
                SharedPrefUtils.setAuthSecret(activity!!.applicationContext, result.data.authToken.secret)
                SharedPrefUtils.setLoginState(activity!!.applicationContext)
                mainViewModel.twitterSessionData.value = result.data
                listener?.onLoginSuccessful(result)
            }

            override fun failure(exception: TwitterException) {
                Snackbar.make(root, exception.message!!, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        login_button.onActivityResult(requestCode, resultCode, data)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginInteraction) {
            listener = context
        } else {
            throw Exception("must implement LoginInteraction")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface LoginInteraction {
        fun onLoginSuccessful(result: Result<TwitterSession>)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
