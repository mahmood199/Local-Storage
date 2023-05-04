package com.example.localstorage.features.chat

sealed class ChatUIItem {
    data class UserQuery(val message: String) : ChatUIItem()
    data class ServerResponse(val message: String) : ChatUIItem()
}