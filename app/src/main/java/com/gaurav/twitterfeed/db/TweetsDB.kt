package com.gaurav.twitterfeed.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.gaurav.twitterfeed.model.*

/**
 * Created by Gaurav on 03-04-2019.
 */
@Database(
    entities = [Tweet::class, Entities::class, User::class, HashTag::class, Url::class],
    version = 1
)
abstract class TweetsDB : RoomDatabase() {
    abstract fun tweetDao(): TweetsDao
    abstract fun entitiesDao(): EntitiesDao
    abstract fun userDao(): UserDao
}
