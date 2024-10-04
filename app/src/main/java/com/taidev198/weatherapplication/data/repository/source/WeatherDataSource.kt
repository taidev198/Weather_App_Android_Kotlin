package com.taidev198.weatherapplication.data.repository.source

import com.taidev198.weatherapplication.data.model.CurrentWeather
import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.HourlyForecast
import com.taidev198.weatherapplication.data.model.Weather
import com.taidev198.weatherapplication.data.model.WeeklyForecast

class WeatherDataSource {
    interface Local {
        suspend fun insertCurrentWeather(
            current: Weather,
            hourly: Weather,
            daily: Weather,
        )

        suspend fun insertCurrentWeather(weather: Weather)

        suspend fun getAllLocalWeathers(): List<Weather>

        suspend fun getLocalWeather(id: String): Weather?

        suspend fun deleteWeather(id: String)
    }

    interface Remote {
        suspend fun getCurrentWeather(
            city: String,
            language: String,
        ): CurrentWeather

        suspend fun getCurrentLocationWeather(
            lat: Double,
            lon: Double,
            language: String,
        ): CurrentWeather

        suspend fun getHourlyForecast(
            city: String,
            language: String,
        ): HourlyForecast

        suspend fun getWeeklyForecast(
            city: String,
            language: String,
        ): WeeklyForecast
    }

}
