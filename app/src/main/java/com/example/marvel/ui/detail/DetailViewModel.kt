package com.example.marvel.ui.detail

import androidx.lifecycle.ViewModel
import com.example.marvel.repository.model.ApiError
import com.example.marvel.repository.model.DetailResponse
import com.example.marvel.repository.service.MarvelRepository
import com.example.marvel.repository.service.MarvelService
import com.example.marvel.repository.service.MarvelServiceImpl
import com.example.marvel.utils.Common.getHash
import com.example.marvel.utils.Common.publicKey
import com.example.marvel.utils.Common.ts

class DetailViewModel : ViewModel() {

    var delegate: DetailViewModelDelegate? = null
    private val marvelRepository: MarvelRepository = MarvelServiceImpl()

    fun getDetail(id: String) {
        marvelRepository.getDetail(id, ts, publicKey, getHash(),
            object : MarvelService.CallbackResponse<DetailResponse> {
                override fun onResponse(response: DetailResponse) {
                    delegate?.onSuccess(response)
                }

                override fun onFailure(error: ApiError) {
                    delegate?.onFailure(error)
                }
            })
    }
}