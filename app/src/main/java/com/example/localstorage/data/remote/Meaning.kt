package com.example.localstorage.data.remote

data class Meaning(
    val title: String,
    val description: String,
    val imageUrl: String = "",
    val redirectUrl: String
)