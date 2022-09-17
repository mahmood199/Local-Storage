package com.example.localstorage.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localstorage.data.dao.EmployeeDao

@Database(entities = [Employee::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

}