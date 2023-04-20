package com.test.energyaustralia.data.repository.dataSourceImpl

import com.test.energyaustralia.data.api.FestivalAPIService
import com.test.energyaustralia.data.repository.dataSource.FestivalsRemoteDataSource
import retrofit2.Response
import com.test.energyaustralia.data.model.APIResponse

class FestivalsRemoteDataSourceImpl(
    private val festivalAPIService: FestivalAPIService
) : FestivalsRemoteDataSource {
    override suspend fun getFestivals(): Response<List<APIResponse>> {
        return festivalAPIService.getFestivals()
    }
}