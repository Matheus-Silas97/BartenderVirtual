package com.example.letsdrink.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.letsdrink.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_drinks")
    fun getAll(): List<FavoriteEntity>

    @Query("SELECT * FROM favorite_drinks WHERE id = :id")
    fun loadDrink(id: Long): FavoriteEntity

    @Insert
    fun insert(drink: FavoriteEntity): Long

    @Query("DELETE FROM favorite_drinks WHERE id = :drinkId")
    fun deleteTraining(drinkId: Long): Int

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_drinks WHERE id = :drinkId LIMIT 1)")
    fun isFavorite(drinkId: Long): Boolean

}