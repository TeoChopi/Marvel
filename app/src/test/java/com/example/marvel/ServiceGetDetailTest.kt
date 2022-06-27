package com.example.marvel

import com.example.marvel.repository.model.DetailResponse
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


class ServiceGetDetailTest {

    @MockK
    private lateinit var apiService: MarvelApi
    private val id = "1009144"

    @Before
    fun setup() {
        apiService = MarvelService().marvelApi
    }

    @Test
    fun `getDetail is successful`() = runBlocking {
        val response = apiService.getDetail(id, ts, publicKey, getHash()).execute()
        // verify the response is Successful
        Assert.assertEquals(true, response.isSuccessful)
    }

    @Test
    fun `getDetail data contains expected values`() {
        val response = apiService.getDetail(id, ts, publicKey, getHash()).execute()
        response.body()?.let { assertServiceDataContainsExpectedValues(it) }
    }

    private fun assertServiceDataContainsExpectedValues(detailResponse: DetailResponse) {
        Assert.assertEquals(1009144, detailResponse.data?.results?.get(0)?.id ?: 0)
        Assert.assertEquals("A.I.M.", detailResponse.data?.results?.get(0)?.name ?: "")
        Assert.assertEquals("AIM is a terrorist organization bent on destroying the world.", detailResponse.data?.results?.get(0)?.description ?: "")
    }

    @Test
    fun `getDetail is not successful when id is Null`() = runBlocking {
        val response = apiService.getDetail("", ts, publicKey, getHash()).execute()
        // verify the response is Successful
        Assert.assertEquals(false, response.isSuccessful)
    }
}