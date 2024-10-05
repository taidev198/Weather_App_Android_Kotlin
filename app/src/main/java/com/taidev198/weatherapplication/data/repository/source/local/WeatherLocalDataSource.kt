package com.taidev198.weatherapplication.data.repository.source.local

import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.local.dao.FavouriteDAO
import com.taidev198.weatherapplication.data.repository.source.local.dao.WeatherDAO

class WeatherLocalDataSource(
    private val weatherDao: WeatherDAO,
    private val favouriteDao: FavouriteDAO
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

    override suspend fun insertFavourite(favourite: FavouriteLocation) {
        favouriteDao.insertFavourite(favourite)
    }

    override suspend fun getAllFavourite(): List<FavouriteLocation> {
        return favouriteDao.getAllFavourite()
    }

    override suspend fun removeFavouriteItem(favouriteId: Long) {
        favouriteDao.removeFavouriteItem(favouriteId)
    }

    override suspend fun isFavoriteLocationExists(cityName: String): Int {
        return favouriteDao.countCityByName(cityName)
    }

}