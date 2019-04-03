package com.gaurav.twitterfeed.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gaurav.twitterfeed.databinding.FragmentFeedBinding
import com.gaurav.twitterfeed.network.RestClient
import com.twitter.sdk.android.core.TwitterCore

class FeedFragment : Fragment() {

    private var listener: OnFeedInteractionListener? = null

    private lateinit var mainViewModel: MainViewModel
    private lateinit var feedViewModel: FeedsViewModel
    lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        val factory = FeedsViewModel.Factory(activity!!)
        feedViewModel = ViewModelProviders.of(this, factory).get(FeedsViewModel::class.java)
        mainViewModel.twitterSessionData.observe(this, Observer { twitterSession ->
            val customApiClient = RestClient.twitterClient(twitterSession!!)
            TwitterCore.getInstance().addApiClient(twitterSession, customApiClient)
            feedViewModel.getFeeds(customApiClient).observe(this, Observer { resource ->
                resource?.let {
                    it.data?.run {
                        binding.progressBar.visibility = View.GONE
                        binding.rvFeeds.adapter = FeedAdapter(this)
                    }
                }
            })
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFeedInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFeedInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFeedInteractionListener

    companion object {
        @JvmStatic
        fun newInstance() = FeedFragment()
    }
}
