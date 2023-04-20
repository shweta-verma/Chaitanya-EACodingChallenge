package com.test.energyaustralia.data.repository.dataSource

import com.test.energyaustralia.data.model.APIResponse
import retrofit2.Response

interface FestivalsRemoteDataSource {
    suspend fun getFestivals(): Response<List<APIResponse>>
}