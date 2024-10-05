package com.taidev198.weatherapplication.data.repository.source

import com.taidev198.weatherapplication.data.model.CurrentWeather
import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.HourlyForecast
import com.taidev198.weatherapplication.data.model.Weather
import com.taidev198.weatherapplication.data.model.WeeklyForecast
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity

class WeatherDataSource {
    interface Local {
        suspend fun insertCurrentWeather(
            current: WeatherEntity,
            hourly: WeatherEntity,
            daily: WeatherEntity,
        )

        suspend fun insertCurrentWeather(weather: WeatherEntity)

        suspend fun getAllLocalWeathers(): List<WeatherEntity>

        suspend fun getLocalWeather(id: String): WeatherEntity?

        suspend fun deleteWeather(id: String)

        suspend fun insertFavourite(favourite: FavouriteLocation)

        suspend fun getAllFavourite(): List<FavouriteLocation>

        suspend fun removeFavouriteItem(favouriteId: Long)

        suspend fun isFavoriteLocationExists(cityName: String): Int
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
