package com.sdk.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdk.domain.model.Food

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDatabase: RoomDatabase() {
    abstract val dao: FoodDao
}