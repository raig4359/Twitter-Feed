package com.gaurav.twitterfeed.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/**
 * Created by Gaurav on 03-04-2019.
 */
class EntitiesWithHashTag {

    @Embedded
    var response: Entities? = null

    @Relation(parentColumn = "id", entityColumn = "eId")
    var topStories: List<HashTag>? = null
}