package com.taidev198.weatherapplication.data.repository.source.local

import com.taidev198.weatherapplication.data.model.CurrentWeather
import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.HourlyForecast
import com.taidev198.weatherapplication.data.model.WeeklyForecast
import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.local.dao.WeatherDAO

class WeatherLocalDataSource(private val weatherDap: WeatherDAO):
WeatherDataSource.Local{

    override suspend fun getSelectedLocation(key: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun isFavouriteLocationExists(cityName: String, countryName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentWeatherLocal() {
        TODO("Not yet implemented")
    }

    override suspend fun saveCurrentWeather(currentWeather: CurrentWeather) {
        TODO("Not yet implemented")
    }

    override suspend fun getWeeklyForecastLocal(city: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveWeeklyForecastLocal(weeklyForecast: WeeklyForecast) {
        TODO("Not yet implemented")
    }

    override suspend fun getHourlyForecastLocal(city: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveHourlyForecastLocal(hourlyForecast: HourlyForecast) {
        TODO("Not yet implemented")
    }

    override suspend fun insertFavoriteWeather(favouriteLocation: FavouriteLocation) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFavorite(): List<FavouriteLocation> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavoriteItem(id: Long) {
        TODO("Not yet implemented")
    }
}