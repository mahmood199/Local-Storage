package com.example.localstorage.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localstorage.data.dao.EmployeeDao
import com.example.localstorage.data.model.Employee

@Database(entities = [Employee::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

}