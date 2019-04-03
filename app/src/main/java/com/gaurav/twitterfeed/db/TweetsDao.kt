package com.gaurav.twitterfeed.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.gaurav.twitterfeed.model.Tweet
import com.gaurav.twitterfeed.model.TweetWithEntities

/**
 * Created by Gaurav on 03-04-2019.
 */
@Dao
interface TweetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweets(vararg tweets: Tweet): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweets(tweets: List<Tweet>): LongArray

    @Query("SELECT * FROM Tweet")
    fun getTweets(): List<TweetWithEntities>

}
