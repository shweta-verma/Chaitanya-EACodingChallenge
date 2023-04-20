package com.test.energyaustralia.presentation.di

import com.test.energyaustralia.domain.usecase.*
import com.test.energyaustralia.domain.FestivalsRepository
import com.test.energyaustralia.domain.usecase.GetFestivalsDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetFestivalsUseCase(
        festivalsRepository: FestivalsRepository,
    ): GetFestivalsDataUseCase {
        return GetFestivalsDataUseCase(festivalsRepository)
    }
}


















