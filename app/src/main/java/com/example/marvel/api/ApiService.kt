package com.example.marvel.api

import com.example.marvel.models.ApiResponse
import com.example.marvel.models.Comic
import com.example.marvel.models.Hero
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Path
import retrofit.http.Query


interface ApiService {
    @GET("characters")
    fun getHeroes(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("limit") limit: Int
    ): Call<ApiResponse<Hero>>


    @GET("characters/{id}")
    fun getHeroById(
        @Path("id") id: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Call<ApiResponse<Hero>>

    @GET("characters/{id}/comics")
    fun getHeroComicsById(
        @Path("id") id: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Call<ApiResponse<List<Comic>>>


    @GET("comics")
    fun getComics(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("limit") limit: Int
    ): Call<ApiResponse<Comic>>

    @GET("comics/{id}")
    fun getComicById(
        @Path("id") id: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Call<ApiResponse<Comic>>
}