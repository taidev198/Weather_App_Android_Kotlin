package com.taidev198.weatherapplication.data.repository

import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import com.taidev198.weatherapplication.data.model.toWeatherEntity
import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class WeatherRepositoryImpl(
    private val localDataSource: WeatherDataSource.Local,
    private val remoteDataSource: WeatherDataSource.Remote,
) : KoinComponent, WeatherRepository {
    override suspend fun isFavoriteLocationExists(cityName: String): Int {
        return localDataSource.isFavoriteLocationExists(cityName)
    }

    override suspend fun insertFavoriteWeather(favouriteLocation: FavouriteLocation) {
        localDataSource.insertFavourite(favouriteLocation)
    }

    override suspend fun getAllFavorite(): List<FavouriteLocation> {
        return localDataSource.getAllFavourite()
    }

    override suspend fun removeFavoriteItem(id: Long) {
        localDataSource.removeFavouriteItem(id)
    }

    override fun getCurrentWeather(city: String, language: String): Flow<WeatherEntity> {
        return flow {
            emit(remoteDataSource.getCurrentWeather(city, language).toWeatherEntity())
        }
    }

    override fun getCurrentLocationWeather(
        lat: Double,
        lon: Double,
        language: String
    ): Flow<WeatherEntity> {
        return flow {
            emit(remoteDataSource.getCurrentLocationWeather(lat, lon, language).toWeatherEntity())
        }
    }

    override fun getHourlyForecast(city: String, language: String): Flow<WeatherEntity> {
        return flow {
            emit(remoteDataSource.getHourlyForecast(city, language).toWeatherEntity())
        }
    }

    override fun getWeeklyForecast(city: String, language: String): Flow<WeatherEntity> {
        return flow {
            emit(remoteDataSource.getWeeklyForecast(city, language).toWeatherEntity())
        }
    }
}