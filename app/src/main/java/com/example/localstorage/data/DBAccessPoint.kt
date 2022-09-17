package com.example.localstorage.data

import android.app.Application
import android.content.Context
import androidx.room.Room

object DBAccessPoint {

    fun getDB(context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            DBLiterals.dbName)
            .build()
    }

}