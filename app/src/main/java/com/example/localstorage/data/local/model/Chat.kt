package com.example.localstorage.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.localstorage.data.local.type_converters.ChatTypeConverter

@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @TypeConverters(ChatTypeConverter::class)
    @ColumnInfo(name = "chat_type")
    val chatType: ChatType,
    val text: String,
    val description: String? = null,
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,
    @ColumnInfo(name = "redirect_url")
    val redirectUrl: String? = null,
    @ColumnInfo(name = "date")
    val date: Long,
)
