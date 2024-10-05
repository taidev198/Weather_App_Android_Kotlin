package com.taidev198.weatherapplication.data.model

import com.google.gson.annotations.Expose
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import com.taidev198.weatherapplication.utils.ext.combineWithCountry

data class CurrentWeather(
    @Expose
    var main: Main? = null,
    @Expose
    var weathers: List<Weather>? = null,
    @Expose
    var wind: Wind? = null,
    @Expose
    var clouds: Clouds? = null,
    @Expose
    var coord: Coord? = null,
    @Expose
    var dt: Long? = null,
    @Expose
    var cityName: String? = null,
    @Expose
    var sys: Sys? = null,
    var day: String = "",
    var iconWeather: String = "",
)

data class Main(
    @Expose
    var  currentTemperature: Double? = null,
    @Expose
    var humidity: Int? = null,
    @Expose
    var tempMin: Double? = null,
    @Expose
    var tempMax: Double? = null,
)

data class Weather(
    @Expose
    var iconWeather: String? = null,
    @Expose
    var main: String? = null,
    @Expose
    var description: String? = null,
)

data class Wind(
    @Expose
    var windSpeed: Double? = null,
)

data class Clouds(
    @Expose
    var percentCloud: Int? = null,
)

data class Coord(
    @Expose
    var lon: Double? = null,
    @Expose
    var lat: Double? = null,
)

data class Sys(
    @Expose
    var country: String? = null,
)

fun CurrentWeather.toWeatherEntity(): WeatherEntity {
    val country: String = sys?.country ?: "Unknown"
    val id = cityName?.combineWithCountry(country) ?: "Unknown"
    val latitude = coord?.lat ?: 0.0
    val longitude = coord?.lon ?: 0.0
    val timeZone = dt ?: 0
    val city = cityName ?: "Unknown"
    val weatherCurrent: WeatherBasic? = this.toWeatherBasic()
    val weatherHourlyList: List<WeatherBasic>? = null
    val weatherDailyList: List<WeatherBasic>? = null

    return WeatherEntity(
        id = id,
        latitude = latitude,
        longitude = longitude,
        timeZone = timeZone,
        city = city,
        country = country,
        weatherCurrent = weatherCurrent,
        weatherHourlyList = weatherHourlyList,
        weatherDailyList = weatherDailyList,
    )
}

fun CurrentWeather.toWeatherBasic(): WeatherBasic? {
    return if (main != null && weathers?.isNotEmpty() == true && wind != null && clouds != null) {
        WeatherBasic(
            dateTime = dt ?: 0,
            currentTemperature = main?.currentTemperature ?: 0.0,
            maxTemperature = main?.tempMax ?: 0.0,
            minTemperature = main?.tempMin ?: 0.0,
            iconWeather = weathers?.firstOrNull()?.iconWeather,
            weatherDescription = weathers?.firstOrNull()?.description,
            humidity = main?.humidity ?: 0,
            percentCloud = clouds?.percentCloud ?: 0,
            windSpeed = wind?.windSpeed ?: 0.0,
        )
    } else {
        null
    }
}