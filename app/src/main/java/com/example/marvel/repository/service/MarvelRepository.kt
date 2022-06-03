package com.example.marvel.repository.service

import com.example.marvel.repository.model.DetailResponse
import com.example.marvel.repository.model.ListResponse

interface MarvelRepository {
    fun getList(ts: String, apikey: String, hash: String, limit: Int, cb: MarvelService.CallbackResponse<ListResponse>)
    fun getDetail(id:String, ts: String, apikey: String, hash: String, cb: MarvelService.CallbackResponse<DetailResponse>)
}