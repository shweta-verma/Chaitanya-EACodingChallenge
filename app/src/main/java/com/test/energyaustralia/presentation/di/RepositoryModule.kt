package com.test.energyaustralia.presentation.di

import com.test.energyaustralia.data.repository.FestivalsRepositoryImpl
import com.test.energyaustralia.data.repository.dataSource.FestivalsRemoteDataSource
import com.test.energyaustralia.domain.FestivalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFestivalsRepository(
        festivalsRemoteDataSource: FestivalsRemoteDataSource,
    ): FestivalsRepository {
        return FestivalsRepositoryImpl(
            festivalsRemoteDataSource
        )
    }

}














