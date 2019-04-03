package com.gaurav.twitterfeed.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.gaurav.twitterfeed.model.User

/**
 * Created by Gaurav on 03-04-2019.
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: User): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>): LongArray

    @Query("SELECT * FROM User where tweetId = :tweetId")
    fun getUser(tweetId: Long): User
}