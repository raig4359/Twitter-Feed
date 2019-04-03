package com.gaurav.twitterfeed.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gaurav.twitterfeed.databinding.LayoutTweetBinding
import com.gaurav.twitterfeed.model.Tweet

/**
 * Created by Gaurav on 03-04-2019.
 */
class FeedAdapter(private val tweets: List<Tweet>) : RecyclerView.Adapter<FeedAdapter.Companion.FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FeedViewHolder {
        val binding = LayoutTweetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(feedViewHolder: FeedViewHolder, position: Int) {
        feedViewHolder.binding.ivProfile.setImageURI(tweets[position].user?.profileImageUrl)
        val name = tweets[position].user?.name ?: "Twitter User"
        feedViewHolder.binding.tvName.text = name
        val screenName = tweets[position].user?.screenName ?: "TwitterUser"
        feedViewHolder.binding.tvScreenName.text = "@" + screenName
        feedViewHolder.binding.tvTweet.text = tweets[position].text
        feedViewHolder.binding.tvFavCount.text = tweets[position].favoriteCount.toString()
        feedViewHolder.binding.tvReTweetCount.text = tweets[position].retweetCount.toString()
    }

    companion object {
        class FeedViewHolder(val binding: LayoutTweetBinding) : RecyclerView.ViewHolder(binding.root)
    }
}