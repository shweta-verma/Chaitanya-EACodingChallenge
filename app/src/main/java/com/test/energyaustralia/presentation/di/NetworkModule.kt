package com.test.energyaustralia.presentation.di

import com.test.energyaustralia.BuildConfig
import com.test.energyaustralia.data.api.FestivalAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideFestivalsAPIService(retrofit: Retrofit): FestivalAPIService {
        return retrofit.create(FestivalAPIService::class.java)
    }

}













