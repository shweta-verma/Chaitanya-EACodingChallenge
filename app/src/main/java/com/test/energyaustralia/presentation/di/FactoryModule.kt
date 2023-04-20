package com.test.energyaustralia.presentation.di

import android.app.Application
import com.test.energyaustralia.domain.usecase.*
import com.test.energyaustralia.presentation.viewModel.FestivalsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideFestivalsViewModelFactory(
        application: Application,
        getFestivalsDataUseCase: GetFestivalsDataUseCase,
    ): FestivalsViewModelFactory {
        return FestivalsViewModelFactory(
            application,
            getFestivalsDataUseCase
        )
    }

}








