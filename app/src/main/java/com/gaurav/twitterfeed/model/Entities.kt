package com.gaurav.twitterfeed.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Gaurav on 03-04-2019.
 */
@Entity(
    foreignKeys = [ForeignKey(
        entity = Tweet::class,
        parentColumns = ["id"],
        childColumns = ["tweetId"]
    )]
)
class Entities {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var tweetId: Long = 0

    @Ignore
    @SerializedName("hashtags")
    @Expose
    var hashTags: List<HashTag>? = null

    @Ignore
    @SerializedName("urls")
    @Expose
    var urls: List<Url>? = null

}