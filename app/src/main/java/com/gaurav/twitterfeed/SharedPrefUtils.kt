package com.gaurav.twitterfeed

import android.content.Context

/**
 * Created by Gaurav on 01-04-2019.
 */
class SharedPrefUtils {

    companion object {

        private const val preference_file_key = "com.gaurav.twitterfeed.tf_preference"
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val USER_ID = "user_id"
        private const val USER_NAME = "user_name"
        private const val AUTH_TOKEN = "auth_token"
        private const val AUTH_SECRET = "auth_secret"

        fun isLoggedIn(context: Context): Boolean {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            return sharedPref.getBoolean(IS_LOGGED_IN, false)
        }

        fun setLoginState(context: Context) {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean(IS_LOGGED_IN, true)
            editor.apply()
        }

        fun setUserId(context: Context, userId: Long) {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putLong(USER_ID, userId)
            editor.apply()
        }

        fun getUserId(context: Context): Long {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            return sharedPref.getLong(USER_ID, 0)
        }


        fun setUserName(context: Context, userName: String) {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(USER_NAME, userName)
            editor.apply()
        }

        fun getUserName(context: Context): String {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            return sharedPref.getString(USER_NAME, "")
        }

        fun setAuthToken(context: Context, authToken: String) {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(AUTH_TOKEN, authToken)
            editor.apply()
        }

        fun getAuthToken(context: Context): String {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            return sharedPref.getString(AUTH_TOKEN, "")
        }

        fun setAuthSecret(context: Context, authSecret: String) {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(AUTH_SECRET, authSecret)
            editor.apply()
        }

        fun getAuthSecret(context: Context): String {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            return sharedPref.getString(AUTH_SECRET, "")
        }


        fun logOut(context: Context) {
            val sharedPref = context.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
        }

    }

}