package com.example.marvel.repository.service

import com.example.marvel.repository.model.DetailResponse
import com.example.marvel.repository.model.ListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelServiceImpl : MarvelRepository {

    override fun getList(ts: String, apikey: String, hash: String, limit:Int, cb: MarvelService.CallbackResponse<ListResponse>) {
        MarvelService().marvelApi.getList(ts, apikey, hash, limit)
            .enqueue(object :Callback<ListResponse> {
                override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>,) {
                    if (response.isSuccessful && response.body() != null) {
                        cb.onResponse(response.body()!!)
                    } else {
                        when (response.code()) {
                            401 -> cb.onFailure(ApiError.InvalidHash(response.code(), response.message()))
                            403 -> cb.onFailure(ApiError.Forbidden(response.code(), response.message()))
                            405 -> cb.onFailure(ApiError.MethodNotAllowed(response.code(), response.message()))
                            409 -> cb.onFailure(ApiError.MissingAPIKey(response.code(), response.message()))
                            else -> cb.onFailure(ApiError.InvalidRequest(response.code(), response.message()))
                        }
                    }
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    cb.onFailure(ApiError.Unknown(-1, t.message.toString()))
                }
            })
    }

    override fun getDetail(id: String, ts: String, apikey: String, hash: String, cb: MarvelService.CallbackResponse<DetailResponse>) {
        MarvelService().marvelApi.getDetail(id, ts, apikey, hash)
            .enqueue(object :Callback<DetailResponse> {
                override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>,) {
                    if (response.isSuccessful && response.body() != null) {
                        cb.onResponse(response.body()!!)
                    } else {
                        when (response.code()) {
                            401 -> cb.onFailure(ApiError.InvalidHash(response.code(), response.message()))
                            403 -> cb.onFailure(ApiError.Forbidden(response.code(), response.message()))
                            405 -> cb.onFailure(ApiError.MethodNotAllowed(response.code(), response.message()))
                            409 -> cb.onFailure(ApiError.MissingAPIKey(response.code(), response.message()))
                            else -> cb.onFailure(ApiError.InvalidRequest(response.code(), response.message()))
                        }
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    cb.onFailure(ApiError.Unknown(-1, t.message.toString()))
                }
            })
    }
}