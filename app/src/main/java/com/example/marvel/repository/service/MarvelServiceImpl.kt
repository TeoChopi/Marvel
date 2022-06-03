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
                        cb.onFailure(Throwable(response.message()), response)
                    }
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    cb.onFailure(t)
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
                        cb.onFailure(Throwable(response.message()), response)
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    cb.onFailure(t)
                }
            })
    }
}