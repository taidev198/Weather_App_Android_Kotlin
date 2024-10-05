package com.taidev198.weatherapplication.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import com.taidev198.weatherapplication.data.repository.source.WeatherRepository
import com.taidev198.weatherapplication.utils.livedata.SingleLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    var currentWeather = SingleLiveData<WeatherEntity>()
    var isLoading = SingleLiveData<Boolean>()
    var errorMessage = SingleLiveData<String>()

    fun fetchCurrentWeather(
        latitude: Double,
        longitude: Double,
        language: String,
    ) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val weatherDeferred =
                    async { weatherRepository.getCurrentLocationWeather(latitude, longitude, language) }
                currentWeather.postValue(weatherDeferred.await().singleOrNull())
                isLoading.postValue(false)
            } catch (e: Exception) {
                Log.v("LCD", "Tai View Model:\n" + e.message.toString())
                errorMessage.postValue(e.message)
                isLoading.postValue(false)
            }
        }
    }

    fun fetchWeatherForSearchResult(
        location: String,
        language: String,
    ) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val weatherDeferred =
                    async { weatherRepository.getCurrentWeather(location, language) }
                currentWeather.postValue(weatherDeferred.await().singleOrNull())
                isLoading.postValue(false)
            } catch (e: Exception) {
                Log.v("LCD", "Tai View Model:\n" + e.message.toString())
                errorMessage.postValue(e.message)
                isLoading.postValue(false)
            }
        }
    }
}
