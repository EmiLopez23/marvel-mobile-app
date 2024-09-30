package com.example.marvel.api

import com.example.marvel.models.ApiResponse
import com.example.marvel.models.Hero
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query


interface ApiService {
    @GET("characters")
    fun getHeroes(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("limit") limit: Int
    ): Call<ApiResponse<Hero>>
}