package com.sdk.data.repository

import com.sdk.data.local.manager.DataStoreManager
import com.sdk.domain.model.FoodType
import com.sdk.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
): LocalRepository {
    override suspend fun saveFoodType(foodType: FoodType) {
        dataStoreManager.saveMealType(foodType)
    }

    override suspend fun getFoodType(): Flow<FoodType> {
        return dataStoreManager.getFoodType()
    }
}