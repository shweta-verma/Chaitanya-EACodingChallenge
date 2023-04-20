package com.test.energyaustralia.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import com.test.energyaustralia.data.model.APIResponse
import com.test.energyaustralia.data.util.Resource
import com.test.energyaustralia.domain.usecase.GetFestivalsDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FestivalsViewModel(
    private val app: Application,
    private val getFestivalsDataUseCase: GetFestivalsDataUseCase,
) : AndroidViewModel(app) {
    val festivalDataList: MutableLiveData<Resource<List<APIResponse>>> = MutableLiveData()

    fun getFestivals() = viewModelScope.launch(Dispatchers.IO) {
        festivalDataList.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getFestivalsDataUseCase.execute()
                festivalDataList.postValue(apiResult)
                Log.v("api result","api result******" +apiResult.data?.get(0)?.festivalName)
            } else {
                festivalDataList.postValue(Resource.Error("Internet is not available"))
            }

        } catch (e: Exception) {
            festivalDataList.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }
}














