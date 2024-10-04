package com.taidev198.weatherapplication.data.repository.source.local.convert

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taidev198.weatherapplication.data.model.WeatherBasic

class WeatherBasicListConverter {

    @TypeConverter
    fun fromWeatherBasicList(weatherBasicList: List<WeatherBasic>?):
            String? {
        return weatherBasicList?.let { Gson().toJson(it)
        }
    }

    @TypeConverter
    fun toWeatherBasicList(json: String?): List<WeatherBasic>? {
        val type = object : TypeToken<List<WeatherBasic>>() {}.type
        return Gson().fromJson(json, type)
    }
}
