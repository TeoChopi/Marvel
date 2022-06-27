package com.example.marvel.ui.list

import androidx.lifecycle.ViewModel
import com.example.marvel.repository.model.ApiError
import com.example.marvel.repository.model.ListResponse
import com.example.marvel.repository.service.MarvelRepository
import com.example.marvel.repository.service.MarvelService
import com.example.marvel.repository.service.MarvelServiceImpl
import com.example.marvel.utils.Common.getHash
import com.example.marvel.utils.Common.publicKey
import com.example.marvel.utils.Common.ts

class ListViewModel : ViewModel() {

    var delegate: ListViewModelDelegate? = null
    private val marvelRepository: MarvelRepository = MarvelServiceImpl()

    fun getList() {
        marvelRepository.getList(ts, publicKey, getHash(), 100,
            object : MarvelService.CallbackResponse<ListResponse> {
                override fun onResponse(response: ListResponse) {
                    delegate?.onSuccess(response)
                }

                override fun onFailure(error: ApiError) {
                    delegate?.onFailure(error)
                }
            })
    }
}