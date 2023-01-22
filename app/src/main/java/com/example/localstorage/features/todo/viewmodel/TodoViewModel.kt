package com.example.localstorage.features.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.localstorage.data.db.DBAccessPoint
import com.example.localstorage.data.model.Todo
import com.example.localstorage.util.FailureStatus
import com.example.localstorage.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(
    application: Application,
) : AndroidViewModel(application) {

    fun getAllTodos() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(DBAccessPoint.getDB(getApplication()).todoDao().getAllTodos()))
        } catch (exception: Exception) {
            emit(Resource.Failure(FailureStatus.API_FAIL, message = exception.message ?: "Error!"))
        }
    }

    fun addTodo(todoTitle: String, todoDescription: String) {
        viewModelScope.launch {
            DBAccessPoint.getDB(getApplication()).todoDao()
                .addTodo(
                    Todo(
                        title = todoTitle,
                        description = todoDescription
                    )
                )
        }
    }

    fun deleteAllTodos() {
        viewModelScope.launch {
            DBAccessPoint.getDB(getApplication()).todoDao()
                .deleteAllTodos()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            DBAccessPoint.getDB(getApplication()).todoDao()
                .deleteTodo(todo)
        }
    }


}