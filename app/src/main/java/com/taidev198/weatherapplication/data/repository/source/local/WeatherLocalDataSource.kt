package com.taidev198.weatherapplication.data.repository.source.local

import com.taidev198.weatherapplication.data.model.Weather
import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.local.dao.WeatherDAO

class WeatherLocalDataSource(
    private val weatherDao: WeatherDAO,
) : WeatherDataSource.Local {
    override suspend fun insertCurrentWeather(
        current: Weather,
        hourly: Weather,
        daily: Weather,
    ) {
        // TODO LATER
    }

    override suspend fun insertCurrentWeather(weather: Weather) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllLocalWeathers(): List<Weather> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocalWeather(id: String): Weather? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWeather(id: String) {
        TODO("Not yet implemented")
    }

}