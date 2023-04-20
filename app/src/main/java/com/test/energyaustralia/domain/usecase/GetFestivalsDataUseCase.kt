package com.test.energyaustralia.domain.usecase

import com.test.energyaustralia.data.model.APIResponse
import com.test.energyaustralia.data.util.Resource
import com.test.energyaustralia.domain.FestivalsRepository

class GetFestivalsDataUseCase(private val festivalRepository: FestivalsRepository) {

    suspend fun execute(): Resource<List<APIResponse>> {
        return festivalRepository.getFestivals()
    }
}