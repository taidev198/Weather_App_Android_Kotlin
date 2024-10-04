package com.taidev198.weatherapplication.data.repository.source.local.convert

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.taidev198.weatherapplication.data.model.WeatherBasic

class WeatherBasicConverter {

    @TypeConverter
    fun fromWeatherBasic(weatherBasic: WeatherBasic?): String? {
        return weatherBasic?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toWeatherBasic(json: String?): WeatherBasic? {
        return json?.let { Gson().fromJson(it, WeatherBasic::class.java) }
    }
}
