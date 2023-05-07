package com.example.localstorage.features.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.localstorage.data.local.dao.ChatDao
import com.example.localstorage.data.local.db.DBAccessPoint
import com.example.localstorage.data.local.model.Chat
import com.example.localstorage.data.local.model.ChatType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatViewModel(private val chatDao: ChatDao) : ViewModel() {

    fun insertChatItem(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            chatDao.insert(Chat(chatType = ChatType.UserQuery, text = message))
            delay(1500)
            chatDao.insert(
                Chat(
                    chatType = ChatType.FromServer,
                    text = "Response : $message",
                    description = "Description $message",
                    imageUrl = "https://source.unsplash.com/user/c_v_r/100x100",
                )
            )
        }
    }

    fun getAllChats() = chatDao.getAllChat()

    companion object {
        const val TAG = "ChatViewModel"
    }


}

class ChatViewModelFactory(
    private val dao: ChatDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}