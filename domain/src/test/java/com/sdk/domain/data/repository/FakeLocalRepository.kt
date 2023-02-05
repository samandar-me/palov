package com.sdk.domain.data.repository

import com.sdk.domain.model.Food
import com.sdk.domain.model.FoodType
import com.sdk.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalRepository : LocalRepository {

    private val fakeFoodList = mutableListOf<Food>()

    override suspend fun saveFoodType(foodType: FoodType) {
        TODO("Not yet implemented")
    }

    override fun getFoodType(): Flow<FoodType> {
        TODO("Not yet implemented")
    }

    override suspend fun saveTheme(index: Int) {
        TODO("Not yet implemented")
    }

    override fun getTheme(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserVisiting(boolean: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getUserVisiting(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavoriteFood(food: Food) {
        fakeFoodList.add(food)
    }

    override fun getFoodById(id: Int): Flow<Food?> {
        return flow {
            emit(fakeFoodList.find { it.foodId == id })
        }
    }

    override fun getFoodList(): Flow<List<Food>> {
        return flow {
            emit(fakeFoodList)
        }
    }

    override suspend fun deleteFood(foodId: Int) {
        fakeFoodList.removeIf { it.foodId == foodId }
    }

    override suspend fun deleteAllFavoriteFoods() {
        fakeFoodList.clear()
    }
}