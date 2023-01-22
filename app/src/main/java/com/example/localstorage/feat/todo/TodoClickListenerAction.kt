package com.example.localstorage.feat.todo

import com.example.localstorage.data.model.Todo

sealed class TodoClickListenerAction {
    data class onLongPress(val todo: Todo, val position: Int) : TodoClickListenerAction()
    data class onClick(val todo: Todo, val position: Int) : TodoClickListenerAction()
}