package com.gaurav.twitterfeed.network

import retrofit2.Response
import java.io.IOException


class ApiResponse<T> {

    val code: Int
    val body: T?
    val errorMessage: String?

    val isSuccessful: Boolean
        get() = code == 200

    val isInternetConnectionError: Boolean
        get() = code == 501

    constructor(error: Throwable) {
        code = if (error is NoConnectivityException) {
            501
        } else {
            500
        }
        body = null
        errorMessage = error.message
    }

    constructor(response: Response<T>) {
        this.code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            if (message == null || message.trim().isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
    }

}