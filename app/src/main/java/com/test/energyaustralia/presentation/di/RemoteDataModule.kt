package com.test.energyaustralia.presentation.di

import com.test.energyaustralia.data.api.FestivalAPIService
import com.test.energyaustralia.data.repository.dataSource.FestivalsRemoteDataSource
import com.test.energyaustralia.data.repository.dataSourceImpl.FestivalsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideFestivalsRemoteDataSource(
        festivalAPIService: FestivalAPIService,
    ): FestivalsRemoteDataSource {
        return FestivalsRemoteDataSourceImpl(festivalAPIService)
    }

}












