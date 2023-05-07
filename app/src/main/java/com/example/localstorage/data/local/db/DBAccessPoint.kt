package com.example.localstorage.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.localstorage.data.local.dao.ChatDao
import com.example.localstorage.data.local.model.Chat

object DBAccessPoint {

    fun getDB(context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            DBLiterals.dbName
        )
            .build()
    }

}

@Database(entities = [Chat::class], version = 1)
abstract class AppDatabaseV2: RoomDatabase() {
    abstract fun chatDao(): ChatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseV2? = null

        fun getDatabase(context: Context): AppDatabaseV2 {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabaseV2::class.java,
                    "app_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
