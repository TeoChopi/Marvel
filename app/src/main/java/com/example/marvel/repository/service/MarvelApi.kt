package com.example.marvel.repository.service

import com.example.marvel.repository.model.DetailResponse
import com.example.marvel.repository.model.ListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApi {

    @GET("v1/public/characters")
    fun getList(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
    ): Call<ListResponse>

    @GET("v1/public/characters/{id}")
    fun getDetail(
        @Path("id") id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<DetailResponse>
}