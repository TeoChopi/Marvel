package com.example.marvel.ui.detail

import com.example.marvel.repository.model.ApiError
import com.example.marvel.repository.model.DetailResponse
import com.example.marvel.repository.model.ListResponse


interface DetailViewModelDelegate {
    fun onSuccess(detail: DetailResponse?)
    fun onFailure(error: ApiError)
}