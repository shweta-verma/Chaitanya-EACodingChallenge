package com.test.energyaustralia.domain

import com.test.energyaustralia.data.model.APIResponse
import com.test.energyaustralia.data.util.Resource

interface FestivalsRepository {
    suspend fun getFestivals(): Resource<List<APIResponse>>
}