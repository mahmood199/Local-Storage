package com.example.localstorage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.localstorage.data.local.model.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chat: Chat): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(chat: List<Chat>): List<Long>

    @Query("SELECT * FROM chat ORDER BY date ASC")
    fun getAllChat(): Flow<List<Chat>>

    fun getAllUnique() = getAllChat().distinctUntilChanged()

}