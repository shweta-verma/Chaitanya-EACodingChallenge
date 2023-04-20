package com.test.energyaustralia.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.energyaustralia.domain.usecase.*

class FestivalsViewModelFactory(
    private val app: Application,
    private val getFestivalsDataUseCase: GetFestivalsDataUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FestivalsViewModel(
            app,
            getFestivalsDataUseCase
        ) as T
    }
}









