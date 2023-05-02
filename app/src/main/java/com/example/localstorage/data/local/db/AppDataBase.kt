package com.example.localstorage.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localstorage.data.local.dao.ChatDao
import com.example.localstorage.data.local.dao.EmployeeDao
import com.example.localstorage.data.local.dao.TodoDao
import com.example.localstorage.data.local.model.Chat
import com.example.localstorage.data.local.model.Employee
import com.example.localstorage.data.local.model.Todo

@Database(entities = [Employee::class, Todo::class, Chat::class], version = 3)
abstract class AppDataBase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    abstract fun todoDao(): TodoDao

    abstract fun chatDao(): ChatDao

}