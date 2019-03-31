package com.gaurav.twitterfeed.network

import android.support.annotation.Nullable


class Resource<T> private constructor(val status: Status, @Nullable val data: T,
                                      @Nullable val message: String) {
    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> loading(@Nullable data: T): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }

        fun <T> error(msg: String, @Nullable data: T): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> failure(@Nullable data: T): Resource<T> {
            return Resource(Status.FAILURE, data, "")
        }

        fun <T> noInternetConnection(@Nullable data: T): Resource<T> {
            return Resource(Status.NO_INTERNET, data, "Internet Connection is not available")
        }
    }

}