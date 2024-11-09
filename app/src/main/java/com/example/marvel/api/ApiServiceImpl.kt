package com.example.marvel.api

import android.content.Context
import android.widget.Toast
import com.example.marvel.R
import com.example.marvel.models.ApiResponse
import com.example.marvel.models.Comic
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
            20
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

    fun getHeroById(
        heroId: String,
        context: Context,
        onSuccess: (Hero) -> Unit,
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

        val call: Call<ApiResponse<Hero>> = service.getHeroById(
            heroId,
            context.getString(R.string.api_key),
            context.getString(R.string.hash),
            context.getString(R.string.ts)
        )

        call.enqueue(object : Callback<ApiResponse<Hero>> {
            override fun onResponse(response: Response<ApiResponse<Hero>>, retrofit: Retrofit?) {
                println(heroId)
                loadingFinished()
                if (response.isSuccess) {
                    val hero: Hero = response.body().data.results[0]
                    onSuccess(hero)
                } else {
                    println("ERROR FETCHING DATA")
                    println(response.errorBody().string())
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

    fun getHeroComicsById(
        heroId: String,
        context: Context,
        onSuccess: (List<Comic>) -> Unit,
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

        val call: Call<ApiResponse<List<Comic>>> = service.getHeroComicsById(
            heroId,
            context.getString(R.string.api_key),
            context.getString(R.string.hash),
            context.getString(R.string.ts)
        )

        call.enqueue(object : Callback<ApiResponse<List<Comic>>> {
            override fun onResponse(
                response: Response<ApiResponse<List<Comic>>>,
                retrofit: Retrofit?
            ) {
                println(heroId)
                println(response)
                loadingFinished()
                if (response.isSuccess) {
                    val comics: List<Comic> = response.body().data.results[0]
                    onSuccess(comics)
                } else {

                    println("ERROR FETCHING DATA")
                    println(response.errorBody().string())
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

    fun getComics(
        context: Context,
        onSuccess: (List<Comic>) -> Unit,
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

        val call: Call<ApiResponse<Comic>> = service.getComics(
            context.getString(R.string.api_key),
            context.getString(R.string.hash),
            context.getString(R.string.ts),
            20
        )

        call.enqueue(object : Callback<ApiResponse<Comic>> {
            override fun onResponse(response: Response<ApiResponse<Comic>>, retrofit: Retrofit?) {
                loadingFinished()
                if (response.isSuccess) {
                    val comics: List<Comic> = response.body().data.results
                    onSuccess(comics)
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

    fun getComicById(
        comicId: String,
        context: Context,
        onSuccess: (Comic) -> Unit,
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

        val call: Call<ApiResponse<Comic>> = service.getComicById(
            comicId,
            context.getString(R.string.api_key),
            context.getString(R.string.hash),
            context.getString(R.string.ts)
        )

        call.enqueue(object : Callback<ApiResponse<Comic>> {
            override fun onResponse(response: Response<ApiResponse<Comic>>, retrofit: Retrofit?) {
                println(comicId)
                loadingFinished()
                if (response.isSuccess) {
                    val comic: Comic = response.body().data.results[0]
                    onSuccess(comic)
                } else {
                    println("ERROR FETCHING DATA")
                    println(response.errorBody().string())
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