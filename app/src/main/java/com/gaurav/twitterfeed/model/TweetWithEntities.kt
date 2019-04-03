package com.gaurav.twitterfeed.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/**
 * Created by Gaurav on 03-04-2019.
 */
class TweetWithEntities {

    @Embedded
    var tweet: Tweet? = null

    @Relation(parentColumn = "id", entityColumn = "tweetId")
    var entities: List<Entities>? = null
}