package com.example.marvel.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvel.repository.model.ListResponse
import com.example.marvel.repository.service.MarvelRepository
import com.example.marvel.repository.service.MarvelService
import com.example.marvel.repository.service.MarvelServiceImpl
import com.example.marvel.utils.Common.getHash
import com.example.marvel.utils.Common.publicKey
import com.example.marvel.utils.Common.ts
import retrofit2.Response

class ListViewModel : ViewModel() {

    private val marvelRepository: MarvelRepository = MarvelServiceImpl()

    fun getList() : MutableLiveData<ListResponse> {

        val liveData: MutableLiveData<ListResponse> = MutableLiveData()

        marvelRepository.getList(ts, publicKey, getHash(), 100,
            object : MarvelService.CallbackResponse<ListResponse> {
                override fun onResponse(response: ListResponse) {
                    liveData.postValue(response)
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    liveData.postValue(null)
                }
            })

        return liveData
    }
}