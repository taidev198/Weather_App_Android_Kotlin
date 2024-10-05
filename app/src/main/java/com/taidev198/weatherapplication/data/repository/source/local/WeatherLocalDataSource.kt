package com.taidev198.weatherapplication.data.repository.source.local

import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.local.dao.WeatherDAO

class WeatherLocalDataSource(
    private val weatherDao: WeatherDAO,
) : WeatherDataSource.Local {
    override suspend fun insertCurrentWeather(
        current: WeatherEntity,
        hourly: WeatherEntity,
        daily: WeatherEntity,
    ) {
        // TODO Implement later
    }

    override suspend fun insertCurrentWeather(weather: WeatherEntity) {
        // TODO Implement later
    }

    override suspend fun getAllLocalWeathers(): List<WeatherEntity> {
        return weatherDao.getAllData()
    }

    override suspend fun getLocalWeather(id: String): WeatherEntity? {
        return weatherDao.getWeather(id)
    }

    override suspend fun deleteWeather(id: String) {
        // TODO Implement later
    }

}