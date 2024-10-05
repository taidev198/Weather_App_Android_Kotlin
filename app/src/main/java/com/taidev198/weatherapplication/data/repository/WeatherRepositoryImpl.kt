package com.taidev198.weatherapplication.data.repository

import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.Weather
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import com.taidev198.weatherapplication.data.model.toWeather
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
    override fun getSelectedLocation(key: String): String {
        TODO("Not yet implemented")
    }

    override fun isFavoriteLocationExists(cityName: String, countryName: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun saveCurrentWeather(currentWeather: WeatherEntity) {
        TODO("Not yet implemented")
    }

    override fun saveWeeklyForecastLocal(weeklyForecast: WeatherEntity) {
        TODO("Not yet implemented")
    }

    override fun getLocalWeather(id: String): Weather? {
        TODO("Not yet implemented")
    }

    override fun saveHourlyForecastLocal(hourlyForecast: WeatherEntity) {
        TODO("Not yet implemented")
    }

    override fun insertFavoriteWeather(favouriteLocation: FavouriteLocation) {
        TODO("Not yet implemented")
    }

    override fun getAllFavorite(): List<FavouriteLocation> {
        TODO("Not yet implemented")
    }

    override fun removeFavoriteItem(id: Long) {
        TODO("Not yet implemented")
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
            emit(remoteDataSource.getHourlyForecast(city, language).toWeather())
        }
    }

    override fun getWeeklyForecast(city: String, language: String): Flow<WeatherEntity> {
        return flow {
            emit(remoteDataSource.getWeeklyForecast(city, language).toWeatherEntity())
        }
    }
}