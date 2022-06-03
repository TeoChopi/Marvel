package com.example.marvel

import com.example.marvel.repository.model.ListResponse
import com.example.marvel.repository.service.MarvelApi
import com.example.marvel.repository.service.MarvelService
import com.example.marvel.utils.Common.getHash
import com.example.marvel.utils.Common.publicKey
import com.example.marvel.utils.Common.ts
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ServiceGetListTest {

    @MockK
    private lateinit var apiService: MarvelApi

    @Before
    fun setup() {
        apiService = MarvelService().marvelApi
    }

    @Test
    fun `getList is successful`() {
        val response = apiService.getList(ts, publicKey, getHash(), 100).execute()
        // verify the response is Successful
        Assert.assertEquals(true, response.isSuccessful)
    }

    @Test
    fun `getList data contains expected values`() {
        val response = apiService.getList(ts, publicKey, getHash(), 100).execute()
        response.body()?.let { assertServiceDataContainsExpectedValues(it) }
    }

    private fun assertServiceDataContainsExpectedValues(listResponse: ListResponse) {
        Assert.assertEquals(1011334, listResponse.data?.results?.get(0)?.id ?: 0)
        Assert.assertEquals("3-D Man", listResponse.data?.results?.get(0)?.name ?: "")
        Assert.assertEquals("", listResponse.data?.results?.get(0)?.description ?: "")
    }

    @Test
    fun `getList is not successful when key is failure`() = runBlocking {
        val response = apiService.getList(ts, "failKey", getHash(), 100).execute()
        // verify the response is Successful
        Assert.assertEquals(false, response.isSuccessful)
    }
}