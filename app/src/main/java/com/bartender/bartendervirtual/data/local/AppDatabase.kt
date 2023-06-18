package com.bartender.bartendervirtual.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bartender.bartendervirtual.data.local.dao.FavoriteDao
import com.bartender.bartendervirtual.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favorites(): FavoriteDao

}