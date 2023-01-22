package com.example.localstorage.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.localstorage.data.model.Todo

@Dao
interface TodoDao {

    @Insert
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("Delete from todo")
    suspend fun deleteAllTodos()

    @Query("Select * from todo")
    suspend fun getAllTodos() : List<Todo>

}