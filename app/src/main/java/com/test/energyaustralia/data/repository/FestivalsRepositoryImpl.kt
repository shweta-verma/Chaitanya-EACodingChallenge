package com.test.energyaustralia.data.repository

import com.test.energyaustralia.data.model.APIResponse
import com.test.energyaustralia.data.repository.dataSource.FestivalsRemoteDataSource
import com.test.energyaustralia.data.util.Resource
import com.test.energyaustralia.domain.FestivalsRepository
import retrofit2.Response

class FestivalsRepositoryImpl(
    private val festivalsRemoteDataSource: FestivalsRemoteDataSource,
) : FestivalsRepository {
    override suspend fun getFestivals(): Resource<List<APIResponse>> {
        return responseToResource(festivalsRemoteDataSource.getFestivals())
    }

    private fun responseToResource(response: Response<List<APIResponse>>): Resource<List<APIResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}