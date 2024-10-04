package com.taidev198.weatherapplication.data.repository.source.remote

import com.taidev198.weatherapplication.data.model.CurrentWeather
import com.taidev198.weatherapplication.data.model.HourlyForecast
import com.taidev198.weatherapplication.data.model.WeeklyForecast
import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.remote.api.ApiService
import com.taidev198.weatherapplication.utils.Constant

class WeatherRemoteDataSource(private val apiService: ApiService) :
WeatherDataSource.Remote {

    override suspend fun getCurrentWeather(
        city: String,
        language: String,
    ): CurrentWeather {
        return apiService.getCurrentWeather(
            city,
            Constant.BASE_API_KEY,
            Constant.UNITS_VALUE,
            language,
        )
    }

    override suspend fun getCurrentLocationWeather(
        lat: Double,
        lon: Double,
        language: String,
    ): CurrentWeather {
        return apiService.getCurrentLocationWeather(
            lat,
            lon,
            Constant.BASE_API_KEY,
            Constant.UNITS_VALUE,
            language,
        )
    }

    override suspend fun getHourlyForecast(
        city: String,
        language: String,
    ): HourlyForecast {
        return apiService.getHourlyForecast(
            city,
            Constant.UNITS_VALUE,
            Constant.FORECAST_HOUR,
            Constant.BASE_API_KEY,
            language,
        )
    }

    override suspend fun getWeeklyForecast(
        city: String,
        language: String,
    ): WeeklyForecast {
        return apiService.getWeeklyForecast(
            city,
            Constant.UNITS_VALUE,
            Constant.FORECAST_DAY,
            Constant.BASE_API_KEY,
            language,
        )
    }
}