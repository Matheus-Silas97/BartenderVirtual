package com.example.letsdrink.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.letsdrink.data.local.entity.FavoriteEntity

interface FavoriteDao {

    @Query("SELECT * FROM favorite_drinks")
    fun getAll(): List<FavoriteEntity>

    @Query("SELECT * FROM favorite_drinks WHERE id = :id")
    fun loadDrink(id: Int): FavoriteEntity

    @Insert
    fun insert(drink: FavoriteEntity): Int

    @Delete
    fun deleteTraining(drink: FavoriteEntity): Int

}