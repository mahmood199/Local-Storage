package com.example.localstorage.features.chat

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.localstorage.data.local.db.DBAccessPoint
import com.example.localstorage.data.local.model.Chat
import com.example.localstorage.data.local.model.ChatType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatViewModel(private val application: Application) : AndroidViewModel(application) {

    init {
        viewModelScope.launch {
            DBAccessPoint.getDB(application).chatDao().getAllChat().collect {
                Log.d(TAG, "Normal size - ${it.size}")
            }

            DBAccessPoint.getDB(application).chatDao().getAllUnique().collect {
                Log.d(TAG, "Unique size - ${it.size}")
            }
        }
    }

    fun insertChatItem(message: String) {
        viewModelScope.launch {
            DBAccessPoint.getDB(application).chatDao().insert(
                Chat(chatType = ChatType.UserQuery, text = message, date = System.currentTimeMillis())
            )
        }
    }

    companion object {
        const val TAG = "ChatViewModel"
    }


}