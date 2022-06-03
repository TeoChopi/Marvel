package com.example.marvel.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvel.repository.model.DetailResponse
import com.example.marvel.repository.service.MarvelRepository
import com.example.marvel.repository.service.MarvelService
import com.example.marvel.repository.service.MarvelServiceImpl
import com.example.marvel.utils.Common.getHash
import com.example.marvel.utils.Common.publicKey
import com.example.marvel.utils.Common.ts
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val marvelRepository: MarvelRepository = MarvelServiceImpl()

    fun getDetail(id: String) : MutableLiveData<DetailResponse> {

        val liveData: MutableLiveData<DetailResponse> = MutableLiveData()

        marvelRepository.getDetail(id, ts, publicKey, getHash(),
            object : MarvelService.CallbackResponse<DetailResponse> {
                override fun onResponse(response: DetailResponse) {
                    liveData.postValue(response)
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    liveData.postValue(null)
                }
            })

        return liveData
    }
}