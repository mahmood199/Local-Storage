package com.example.localstorage.data.local.type_converters

import androidx.room.TypeConverter
import com.example.localstorage.data.local.model.ChatType

class ChatTypeConverter {

    @TypeConverter
    fun fromChatType(chatType: ChatType): String {
        return when (chatType) {
            ChatType.FromServer -> "from_server"
            ChatType.UserQuery -> "user_query"
            else -> "default"
        }
    }

    @TypeConverter
    fun toChatType(value: String): ChatType {
        return when(value) {
            "from_server" -> ChatType.FromServer
            "user_query" -> ChatType.UserQuery
            else -> ChatType.Default
        }
    }

}