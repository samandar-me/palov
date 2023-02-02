package com.sdk.domain.repository

import com.sdk.domain.model.Food
import com.sdk.domain.model.FoodType
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveFoodType(foodType: FoodType)
    fun getFoodType(): Flow<FoodType>
    suspend fun saveTheme(index: Int)
    suspend fun getTheme(): Flow<Int>

    suspend fun saveFavoriteFood(food: Food)
    fun getFoodById(id: Int): Flow<Food?>
    fun getFoodList(): Flow<List<Food>>
    suspend fun deleteFood(foodId: Int)
    suspend fun deleteAllFavoriteFoods()
}