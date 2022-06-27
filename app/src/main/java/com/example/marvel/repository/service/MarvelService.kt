package com.example.marvel.repository.service

import com.example.marvel.repository.model.ApiError
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarvelService {

    interface CallbackResponse<T> {
        fun onResponse(response: T)
        fun onFailure(error: ApiError)
    }

    val marvelApi: MarvelApi

    init {

        val timeout = 45000L

        val client = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(com.example.marvel.BuildConfig.URL_BASE)
            .build()

        marvelApi = retrofit.create(MarvelApi::class.java)
    }
}
