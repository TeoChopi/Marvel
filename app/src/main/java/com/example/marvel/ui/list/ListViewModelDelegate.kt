package com.example.marvel.ui.list

import com.example.marvel.repository.model.ApiError
import com.example.marvel.repository.model.ListResponse


interface ListViewModelDelegate {
    fun onSuccess(list: ListResponse?)
    fun onFailure(error: ApiError)
}