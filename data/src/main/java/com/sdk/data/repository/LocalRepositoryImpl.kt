package com.sdk.data.repository

import com.sdk.data.local.database.FoodDao
import com.sdk.data.local.manager.DataStoreManager
import com.sdk.domain.model.Food
import com.sdk.domain.model.FoodType
import com.sdk.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val dao: FoodDao
) : LocalRepository {
    override suspend fun saveFoodType(foodType: FoodType) {
        dataStoreManager.saveMealType(foodType)
    }

    override fun getFoodType(): Flow<FoodType> {
        return dataStoreManager.getFoodType()
    }

    override suspend fun saveTheme(index: Int) {
        dataStoreManager.saveTheme(index)
    }

    override fun getTheme(): Flow<Int> {
        return dataStoreManager.getTheme()
    }

    override suspend fun saveUserVisiting(boolean: Boolean) {
        dataStoreManager.saveUserVisiting(boolean)
    }

    override fun getUserVisiting(): Flow<Boolean> {
        return dataStoreManager.getUserVisiting()
    }

    override suspend fun saveFavoriteFood(food: Food) {
        dao.saveFavoriteFood(food)
    }

    override fun getFoodById(id: Int): Flow<Food?> {
        return dao.getFoodById(id)
    }

    override fun getFoodList(): Flow<List<Food>> {
        return dao.getAllFavorites()
    }

    override suspend fun deleteFood(foodId: Int) {
        dao.deleteFavoriteFood(foodId)
    }

    override suspend fun deleteAllFavoriteFoods() {
        dao.deleteAllFavoriteFoods()
    }
}