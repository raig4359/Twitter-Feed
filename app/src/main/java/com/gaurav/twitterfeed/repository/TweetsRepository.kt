package com.gaurav.twitterfeed.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.gaurav.twitterfeed.db.DBManager
import com.gaurav.twitterfeed.db.EntitiesDao
import com.gaurav.twitterfeed.db.TweetsDao
import com.gaurav.twitterfeed.db.UserDao
import com.gaurav.twitterfeed.model.*
import com.gaurav.twitterfeed.network.ApiResponse
import com.gaurav.twitterfeed.network.CustomTwitterClient
import com.gaurav.twitterfeed.network.NetworkBoundResource
import com.gaurav.twitterfeed.network.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

/**
 * Created by Gaurav on 03-04-2019.
 */
class TweetsRepository(val context: Context) {


    fun getFeeds(customApiClient: CustomTwitterClient?): LiveData<Resource<List<Tweet>?>> {

        return object : NetworkBoundResource<List<Tweet>, List<Tweet>>() {
            override fun saveCallResult(tweets: List<Tweet>?) {

                if (tweets != null) {

                    val tweetsDao: TweetsDao? = DBManager.getDbManager()
                        ?.getDatabase(context = context)
                        ?.tweetDao()

                    val entitiesDao: EntitiesDao? = DBManager.getDbManager()
                        ?.getDatabase(context = context)
                        ?.entitiesDao()

                    val userDao: UserDao? = DBManager.getDbManager()
                        ?.getDatabase(context = context)
                        ?.userDao()

                    val tweetsList = ArrayList<Tweet>()
                    val entitiesList = ArrayList<Entities>()
                    val userList = ArrayList<User>()
                    val urls = ArrayList<Url>()
                    val hashTags = ArrayList<HashTag>()

                    for (tweet in tweets) {
                        tweetsList.add(tweet)

                        tweet.entities?.tweetId = tweet.id
                        entitiesList.add(tweet.entities!!)

                        tweetsDao?.insertTweets(tweet)
                        val eId = entitiesDao?.insertEntities(tweet.entities!!)

                        tweet.user?.tweetId = tweet.id
                        userList.add(tweet.user!!)

                        for (url in tweet.entities?.urls!!) {
                            url.eId = eId!![0]
                        }
                        urls.addAll(tweet.entities?.urls!!)

                        for (hashTag in tweet.entities?.hashTags!!) {
                            hashTag.eId = eId!![0]
                        }
                        hashTags.addAll(tweet.entities?.hashTags!!)
                    }

                    entitiesDao?.insertUrls(urls)
                    entitiesDao?.insertHashTags(hashTags)
                    userDao?.insertUsers(userList)
                }


            }

            override fun shouldFetch(item: List<Tweet>): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Tweet>> {
                val data = MutableLiveData<List<Tweet>>()

                val tweetsDao: TweetsDao? = DBManager.getDbManager()
                    ?.getDatabase(context = context)
                    ?.tweetDao()

                val entitiesDao: EntitiesDao? = DBManager.getDbManager()
                    ?.getDatabase(context = context)
                    ?.entitiesDao()

                val userDao: UserDao? = DBManager.getDbManager()
                    ?.getDatabase(context = context)
                    ?.userDao()

                val tweets = ArrayList<Tweet>()

                val executorService = Executors.newSingleThreadExecutor()

                executorService.execute {

                    val tweetsEntities = tweetsDao?.getTweets()

                    for (tweetEnt in tweetsEntities!!) {
                        val hashTag = entitiesDao?.getHashTag(tweetEnt.entities?.get(0)?.id!!)
                        val urls = entitiesDao?.getUrl(tweetEnt.entities?.get(0)?.id!!)
                        val user = userDao?.getUser(tweetEnt.tweet?.id!!)

                        tweetEnt.entities?.get(0)?.urls = urls
                        tweetEnt.entities?.get(0)?.hashTags = hashTag
                        tweetEnt.tweet?.entities = tweetEnt.entities?.get(0)
                        tweetEnt.tweet?.user = user
                        tweets.add(tweetEnt.tweet!!)
                    }

                    data.postValue(tweets)
                }
                return data
            }

            override fun createCall(): LiveData<ApiResponse<List<Tweet>>> {
                val svResponse = MutableLiveData<ApiResponse<List<Tweet>>>()
                customApiClient?.webServices?.getFeeds()?.enqueue(object : Callback<List<Tweet>> {
                    override fun onResponse(
                        call: Call<List<Tweet>>,
                        response: Response<List<Tweet>>
                    ) {
                        svResponse.value = ApiResponse(response)
                    }

                    override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                        svResponse.value = ApiResponse(t)
                    }

                })

                return svResponse
            }

        }.getAsLiveData()
    }
}