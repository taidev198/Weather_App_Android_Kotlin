package com.taidev198.weatherapplication.data.repository.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.taidev198.weatherapplication.data.model.FavouriteLocation
import com.taidev198.weatherapplication.data.model.Weather
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import com.taidev198.weatherapplication.data.repository.source.local.convert.WeatherBasicConverter
import com.taidev198.weatherapplication.data.repository.source.local.convert.WeatherBasicListConverter
import com.taidev198.weatherapplication.data.repository.source.local.dao.FavouriteDao
import com.taidev198.weatherapplication.data.repository.source.local.dao.WeatherDAO

@Database(entities = [WeatherEntity::class, FavouriteLocation::class], version = 2, exportSchema = false)@TypeConverters(WeatherBasicConverter::class, WeatherBasicListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
    abstract fun favouriteDao(): FavouriteDao

    companion object {
        private const val DB_NAME = "Weather.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME,
            ).build()
    }
}