package com.example.localstorage.features.todo

import com.example.localstorage.data.local.model.Todo

sealed class TodoClickListenerAction {
    data class onLongPress(val todo: Todo, val position: Int) : TodoClickListenerAction()
    data class onClick(val todo: Todo, val position: Int) : TodoClickListenerAction()
}