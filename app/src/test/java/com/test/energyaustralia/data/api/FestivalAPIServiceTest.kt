package com.test.energyaustralia.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FestivalAPIServiceTest {
    private lateinit var service: FestivalAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FestivalAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @Test
    fun getFestivals_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("festivalsresponse.json")
            val responseBody = service.getFestivals().body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/api/v1/festivals")
        }
    }

    @Test
    fun getFestivals_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("festivalsresponse.json")
            val responseBody = service.getFestivals().body()
            val articlesList = responseBody!!.toList()
            assertThat(articlesList.size).isEqualTo(1)
        }
    }

    @Test
    fun getFestivals_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("festivalsresponse.json")
            val responseBody = service.getFestivals().body()
            val bandsList = responseBody!!.toList().get(0).bands
            val band = bandsList[0]
            assertThat(band.name).isEqualTo("Summon")
            assertThat(band.recordLabel).isEqualTo("Outerscope")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}