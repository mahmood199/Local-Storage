package com.example.localstorage

import android.app.Application
import com.example.localstorage.data.local.db.AppDatabaseV2

class LocalStorageApplication : Application() {
    val database: AppDatabaseV2 by lazy { AppDatabaseV2.getDatabase(this) }
}