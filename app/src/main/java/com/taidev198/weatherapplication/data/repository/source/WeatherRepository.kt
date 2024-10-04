package com.taidev198.weatherapplication.data.repository.source

import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.Weather
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getSelectedLocation(key: String): String

    fun isFavoriteLocationExists(
        cityName: String,
        countryName: String,
    ): Boolean

    fun saveCurrentWeather(currentWeather: WeatherEntity)

    fun saveWeeklyForecastLocal(weeklyForecast: WeatherEntity)

    fun getLocalWeather(id: String): Weather?

    fun saveHourlyForecastLocal(hourlyForecast: WeatherEntity)

    fun insertFavoriteWeather(favouriteLocation: FavouriteLocation)

    fun getAllFavorite(): List<FavouriteLocation>

    fun removeFavoriteItem(id: Long)

    fun getCurrentWeather(
        city: String,
        language: String,
    ): Flow<WeatherEntity>

    fun getCurrentLocationWeather(
        lat: Double,
        lon: Double,
        language: String,
    ): Flow<WeatherEntity>

    fun getHourlyForecast(
        city: String,
        language: String,
    ): Flow<WeatherEntity>

    fun getWeeklyForecast(
        city: String,
        language: String,
    ): Flow<WeatherEntity>
}