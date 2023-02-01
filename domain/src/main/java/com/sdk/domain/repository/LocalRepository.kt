package com.sdk.domain.repository

import com.sdk.domain.model.Food
import com.sdk.domain.model.FoodType
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveFoodType(foodType: FoodType)
    suspend fun getFoodType(): Flow<FoodType>

    // Room database operations

    suspend fun saveFavoriteFood(food: Food)
    fun getFoodById(id: Int): Flow<Food?>
    fun getFoodList(): Flow<List<Food>>

    suspend fun deleteFood(foodId: Int)
}