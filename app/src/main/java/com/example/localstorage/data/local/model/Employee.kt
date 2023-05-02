package com.example.localstorage.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "employee_name")
    val employeeName: String,
    @ColumnInfo(name = "employee_number")
    val employeeNumber: String,
)
