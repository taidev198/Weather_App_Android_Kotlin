package com.taidev198.weatherapplication.data.repository.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.taidev198.weatherapplication.data.model.FavouriteLocation

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteLocation)

    @Query("SELECT * FROM favourite_locations")
    suspend fun getAllFavourite(): List<FavouriteLocation>

    @Query("DELETE FROM favourite_locations WHERE id = :favouriteId")
    suspend fun removeFavouriteItem(favouriteId: Long)

    @Query("SELECT COUNT(*) FROM favourite_locations WHERE city_name = :cityName")
    suspend fun countCityByName(cityName: String): Int
}