package com.gaurav.twitterfeed.db

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Created by Gaurav on 03-04-2019.
 */
class DBManager {
    private var database: TweetsDB? = null
    private val dbName = "tweeter_app_database"

    fun getDatabase(context: Context): TweetsDB? {
        if (database == null) {
            database = Room.databaseBuilder(context, TweetsDB::class.java, dbName).build()
        }
        return database
    }

    companion object {
        private var dbManager: DBManager? = null

        fun getDbManager(): DBManager? {
            if (dbManager == null) {
                dbManager = DBManager()
            }
            return dbManager
        }
    }
}