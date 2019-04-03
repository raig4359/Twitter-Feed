package com.gaurav.twitterfeed.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
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
class User {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Long = 0

    var tweetId: Long = 0

    @SerializedName("id_str")
    @Expose
    var idStr: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("screen_name")
    @Expose
    var screenName: String? = null

    @SerializedName("location")
    @Expose
    var location: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("protected")
    @Expose
    var _protected: Boolean = false

    @SerializedName("followers_count")
    @Expose
    var followersCount: Long = 0

    @SerializedName("friends_count")
    @Expose
    var friendsCount: Long = 0

    @SerializedName("listed_count")
    @Expose
    var listedCount: Long = 0

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("favourites_count")
    @Expose
    var favouritesCount: Long = 0

    @SerializedName("geo_enabled")
    @Expose
    var geoEnabled: Boolean = false

    @SerializedName("verified")
    @Expose
    var verified: Boolean = false

    @SerializedName("statuses_count")
    @Expose
    var statusesCount: Long = 0

    @SerializedName("lang")
    @Expose
    var lang: String? = null

    @SerializedName("contributors_enabled")
    @Expose
    var contributorsEnabled: Boolean = false

    @SerializedName("is_translator")
    @Expose
    var isTranslator: Boolean = false

    @SerializedName("is_translation_enabled")
    @Expose
    var isTranslationEnabled: Boolean = false

    @SerializedName("profile_background_color")
    @Expose
    var profileBackgroundColor: String? = null

    @SerializedName("profile_background_image_url")
    @Expose
    var profileBackgroundImageUrl: String? = null

    @SerializedName("profile_background_image_url_https")
    @Expose
    var profileBackgroundImageUrlHttps: String? = null

    @SerializedName("profile_background_tile")
    @Expose
    var profileBackgroundTile: Boolean = false

    @SerializedName("profile_image_url")
    @Expose
    var profileImageUrl: String? = null

    @SerializedName("profile_image_url_https")
    @Expose
    var profileImageUrlHttps: String? = null

    @SerializedName("profile_banner_url")
    @Expose
    var profileBannerUrl: String? = null

    @SerializedName("profile_link_color")
    @Expose
    var profileLinkColor: String? = null

    @SerializedName("profile_sidebar_border_color")
    @Expose
    var profileSidebarBorderColor: String? = null

    @SerializedName("profile_sidebar_fill_color")
    @Expose
    var profileSidebarFillColor: String? = null

    @SerializedName("profile_text_color")
    @Expose
    var profileTextColor: String? = null

    @SerializedName("profile_use_background_image")
    @Expose
    var profileUseBackgroundImage: Boolean = false

    @SerializedName("has_extended_profile")
    @Expose
    var hasExtendedProfile: Boolean = false

    @SerializedName("default_profile")
    @Expose
    var defaultProfile: Boolean = false

    @SerializedName("default_profile_image")
    @Expose
    var defaultProfileImage: Boolean = false

    @SerializedName("following")
    @Expose
    var following: Boolean = false

    @SerializedName("follow_request_sent")
    @Expose
    var followRequestSent: Boolean = false

    @SerializedName("notifications")
    @Expose
    var notifications: Boolean = false

    @SerializedName("translator_type")
    @Expose
    var translatorType: String? = null

}