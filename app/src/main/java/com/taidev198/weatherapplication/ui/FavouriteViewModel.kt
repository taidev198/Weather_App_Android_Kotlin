package com.taidev198.weatherapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.repository.source.WeatherRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    private val _favouriteLocations = MutableLiveData<List<FavouriteLocation>>()
    val favouriteLocations: LiveData<List<FavouriteLocation>> = _favouriteLocations

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getAllFavouriteLocations() {
        viewModelScope.launch {
            try {
                val locations = weatherRepository.getAllFavorite()
                _favouriteLocations.value = locations
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun removeFavouriteItem(id: Long) {
        viewModelScope.launch {
            try {
                weatherRepository.removeFavoriteItem(id)
                getAllFavouriteLocations()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
