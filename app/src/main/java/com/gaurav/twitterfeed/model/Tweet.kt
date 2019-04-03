package com.gaurav.twitterfeed.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Gaurav on 03-04-2019.
 */
@Entity
class Tweet {

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("id_str")
    @Expose
    var idStr: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("truncated")
    @Expose
    var truncated: Boolean = false

    @Ignore
    @SerializedName("entities")
    @Expose
    var entities: Entities? = null

    @Ignore
    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("is_quote_status")
    @Expose
    var quoteStatus: Boolean = false

    @SerializedName("retweet_count")
    @Expose
    var retweetCount: Long = 0

    @SerializedName("favorite_count")
    @Expose
    var favoriteCount: Long = 0

    @SerializedName("favorited")
    @Expose
    var favorited: Boolean = false

    @SerializedName("retweeted")
    @Expose
    var retweeted: Boolean = false

    @SerializedName("possibly_sensitive")
    @Expose
    var possiblySensitive: Boolean = false

    @SerializedName("possibly_sensitive_appealable")
    @Expose
    var possiblySensitiveAppealable: Boolean = false

    @SerializedName("lang")
    @Expose
    var lang: String? = null
}