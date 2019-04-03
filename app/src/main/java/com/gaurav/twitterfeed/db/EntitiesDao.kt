package com.gaurav.twitterfeed.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.gaurav.twitterfeed.model.Entities
import com.gaurav.twitterfeed.model.HashTag
import com.gaurav.twitterfeed.model.Url

/**
 * Created by Gaurav on 03-04-2019.
 */
@Dao
interface EntitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntities(vararg entities: Entities): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntities(entities: List<Entities>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHashTags(hashTags: List<HashTag>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUrls(urls: List<Url>): LongArray

    @Query("SELECT * FROM Url where eId = :eId")
    fun getUrl(eId: Long): List<Url>

    @Query("SELECT * FROM HashTag where eId = :eId")
    fun getHashTag(eId: Long): List<HashTag>

}