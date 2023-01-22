package com.example.localstorage.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "todo_title")
    val title: String,
    @ColumnInfo(name = "todo_description")
    val description: String,
)