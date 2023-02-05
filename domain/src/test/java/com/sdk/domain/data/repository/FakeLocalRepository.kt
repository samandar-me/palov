package com.sdk.domain.data.repository

import com.sdk.domain.model.Food
import com.sdk.domain.model.FoodType
import com.sdk.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalRepository : LocalRepository {

    private val fakeFoodList = mutableListOf<Food>()
    private var foodType = FoodType()
    private var isUserVisited = false
    private var savedIndex = 0

    override suspend fun saveFoodType(foodType: FoodType) {
        this.foodType = foodType
    }

    override fun getFoodType(): Flow<FoodType> {
        return flow { emit(foodType) }
    }

    override suspend fun saveTheme(index: Int) {
        savedIndex = index
    }

    override fun getTheme(): Flow<Int> {
        return flow { emit(savedIndex) }
    }

    override suspend fun saveUserVisiting(boolean: Boolean) {
        isUserVisited = boolean
    }

    override fun getUserVisiting(): Flow<Boolean> {
        return flow { emit(isUserVisited) }
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