package com.example.marvel.api

import android.content.Context
import android.widget.Toast
import com.example.marvel.R
import com.example.marvel.models.ApiResponse
import com.example.marvel.models.Hero
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() {
    fun getHeroes(
        context: Context,
        onSuccess: (List<Hero>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.api_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)

        val call: Call<ApiResponse<Hero>> = service.getHeroes(
            context.getString(R.string.api_key),
            context.getString(R.string.hash),
            context.getString(R.string.ts),
            10
        )

        call.enqueue(object : Callback<ApiResponse<Hero>> {
            override fun onResponse(response: Response<ApiResponse<Hero>>, retrofit: Retrofit?) {
                loadingFinished()
                if (response.isSuccess) {
                    val heroes: List<Hero> = response.body().data.results
                    onSuccess(heroes)
                } else {
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(
                    context,
                    context.getString(R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
                onFail()
                loadingFinished()
            }
        }
        )
    }
}