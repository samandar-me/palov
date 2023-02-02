package com.sdk.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdk.domain.model.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFavoriteFood(food: Food)

    @Query("SELECT * FROM Food ORDER BY id DESC")
    fun getAllFavorites(): Flow<List<Food>>

    @Query("SELECT * FROM Food WHERE foodId = :id")
    fun getFoodById(id: Int): Flow<Food?>

    @Query("DELETE FROM Food WHERE foodId = :foodId")
    suspend fun deleteFavoriteFood(foodId: Int)

    @Query("DELETE FROM Food")
    suspend fun deleteAllFavoriteFoods()
}