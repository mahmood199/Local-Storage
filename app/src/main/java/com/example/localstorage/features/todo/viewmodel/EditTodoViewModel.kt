package com.example.localstorage.features.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.localstorage.data.db.DBAccessPoint
import com.example.localstorage.data.model.Todo
import kotlinx.coroutines.launch

class EditTodoViewModel(
    application: Application,
) : AndroidViewModel(application) {

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            DBAccessPoint.getDB(getApplication()).todoDao().updateTodo(todo)
        }
    }

}