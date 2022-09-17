package com.example.localstorage.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployeeDetails(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "specialisation")
    val field: String,
)