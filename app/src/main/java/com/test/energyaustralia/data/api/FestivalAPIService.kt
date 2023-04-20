package com.test.energyaustralia.data.api

import com.test.energyaustralia.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface FestivalAPIService {
    @GET("api/v1/festivals")
    suspend fun getFestivals(
    ): Response<List<APIResponse>>
}