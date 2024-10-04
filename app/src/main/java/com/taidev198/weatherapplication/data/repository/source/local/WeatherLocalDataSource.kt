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
        // TODO Implement later
    }

    override suspend fun insertCurrentWeather(weather: Weather) {
        // TODO Implement later
    }

    override suspend fun getAllLocalWeathers(): List<Weather> {
        return weatherDao.getAllData()
    }

    override suspend fun getLocalWeather(id: String): Weather? {
        return weatherDao.getWeather(id)
    }

    override suspend fun deleteWeather(id: String) {
        // TODO Implement later
    }

}