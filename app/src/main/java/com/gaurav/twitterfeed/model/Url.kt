package com.gaurav.twitterfeed.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.google.common.io.Files.append
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Gaurav on 03-04-2019.
 */
@Entity(
    foreignKeys = [ForeignKey(
        entity = Entities::class,
        parentColumns = ["id"],
        childColumns = ["eId"]
    )]
)
class Url {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var eId: Long = 0

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("expanded_url")
    @Expose
    var expandedUrl: String? = null

    @SerializedName("display_url")
    @Expose
    var displayUrl: String? = null

}