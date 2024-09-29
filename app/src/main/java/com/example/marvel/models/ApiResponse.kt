package com.example.marvel.models

data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val data: Data<T>
)

data class Data<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)