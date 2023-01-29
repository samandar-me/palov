package com.sdk.domain.repository

import com.sdk.domain.model.FoodType
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveFoodType(foodType: FoodType)
    suspend fun getFoodType(): Flow<FoodType>
}