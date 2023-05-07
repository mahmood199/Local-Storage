package com.example.localstorage.features.chat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ChatUIItem(val id: Int): Parcelable {
    @Parcelize
    data class UserQuery(val message: String, val userQueryId: Int) : ChatUIItem(userQueryId)
    @Parcelize
    data class ServerResponse(val message: String, val serverResponseId: Int) : ChatUIItem(serverResponseId)
}