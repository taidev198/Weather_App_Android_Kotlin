package com.taidev198.weatherapplication.data.repository.source

import com.taidev198.weatherapplication.data.model.CurrentWeather
import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.HourlyForecast
import com.taidev198.weatherapplication.data.model.WeeklyForecast

class WeatherDataSource {
    interface Local {
        suspend fun getSelectedLocation(key: String): String
        suspend fun isFavouriteLocationExists(
            cityName: String,
            countryName: String,
        ) : Boolean

        suspend fun getCurrentWeatherLocal()
        suspend fun saveCurrentWeather(currentWeather: CurrentWeather)
        suspend fun getWeeklyForecastLocal(city: String)
        suspend fun saveWeeklyForecastLocal(weeklyForecast: WeeklyForecast)
        suspend fun getHourlyForecastLocal(city: String)
        suspend fun saveHourlyForecastLocal(hourlyForecast: HourlyForecast)
        suspend fun insertFavoriteWeather(favouriteLocation: FavouriteLocation)
        suspend fun getAllFavorite(): List<FavouriteLocation>
        suspend fun removeFavoriteItem(id: Long)
    }

    interface Remote {
        suspend fun getCurrentWeather(city: String, language: String): CurrentWeather
        suspend fun getCurrentLocationWeather(
            lat: Double,
            lon: Double,
            language: String
        ): CurrentWeather

        suspend fun getHourlyForecast(city: String, language: String): HourlyForecast
        suspend fun getWeeklyForecast(city: String, language: String): WeeklyForecast
    }

}
