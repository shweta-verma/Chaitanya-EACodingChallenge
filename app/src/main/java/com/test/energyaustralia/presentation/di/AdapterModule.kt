//package com.test.energyaustralia.presentation.di
//
//import com.test.energyaustralia.data.model.APIResponse
//import com.test.energyaustralia.presentation.adapter.FestivalsAdapter
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class AdapterModule {
//    @Singleton
//    @Provides
//    fun provideFestivalsAdapter(
//        recordLabelList: List<APIResponse>,
//        recordLabelCount: Int,
//    ): FestivalsAdapter {
//        return FestivalsAdapter(recordLabelList, recordLabelCount)
//    }
//}