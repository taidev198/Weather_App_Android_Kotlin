package com.taidev198.weatherapplication.data.repository.source

import com.taidev198.weatherapplication.data.model.CurrentWeather
import com.taidev198.weatherapplication.data.model.HourlyForecast
import com.taidev198.weatherapplication.data.model.WeeklyForecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(city: String, language: String): Flow<CurrentWeather>
    fun getCurrentLocationWeather(lat: Double, lon: Double, language: String): Flow<CurrentWeather>
    fun getHourlyForecast(city: String, language: String): Flow<HourlyForecast>
    fun getWeeklyForecast(city: String, language: String): Flow<WeeklyForecast>
}