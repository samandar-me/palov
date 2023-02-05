package com.sdk.domain.domain.use_case.local.room

import com.google.common.truth.Truth.assertThat
import com.sdk.domain.data.repository.FakeLocalRepository
import com.sdk.domain.model.Food
import com.sdk.domain.use_case.local.room.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RoomUseCasesTest {
    private lateinit var fakeLocalRepository: FakeLocalRepository
    private lateinit var getFavoriteFoodsUseCase: GetFavoriteFoodsUseCase
    private lateinit var deleteFavoriteFoodUseCase: DeleteFavoriteFoodUseCase
    private lateinit var saveFavoriteFoodUseCase: SaveFavoriteFoodUseCase
    private lateinit var deleteAllFavoriteFoodsUseCase: DeleteAllFavoriteFoodsUseCase
    private lateinit var getFavoriteFoodByIdUseCase: GetFavoriteFoodByIdUseCase

    @Before
    fun setUp() {
        fakeLocalRepository = FakeLocalRepository()
        getFavoriteFoodsUseCase = GetFavoriteFoodsUseCase(fakeLocalRepository)
        deleteFavoriteFoodUseCase = DeleteFavoriteFoodUseCase(fakeLocalRepository)
        saveFavoriteFoodUseCase = SaveFavoriteFoodUseCase(fakeLocalRepository)
        deleteAllFavoriteFoodsUseCase = DeleteAllFavoriteFoodsUseCase(fakeLocalRepository)
        getFavoriteFoodByIdUseCase = GetFavoriteFoodByIdUseCase(fakeLocalRepository)

        val foodList = mutableListOf<Food>()

        (10..50).forEachIndexed { index, i ->
            foodList.add(
                Food(
                    foodId = i,
                    title = "$index $i",
                    image = "https://$index.com",
                    description = "${index.plus(i)}",
                    likeCount = i * i,
                    time = i * 100,
                    vegetarian = i * 2 > 40,
                    vegan = false,
                    veryHealthy = true,
                    dairyFree = false,
                    cheap = true,
                    glutenFree = true,
                    ingredients = emptyList(),
                    analyzedIns = emptyList()
                )
            )
        }
        runBlocking {
            foodList.forEach {
                fakeLocalRepository.saveFavoriteFood(it)
            }
        }
    }

    @Test
    fun `Save new favorite food and get by id`() = runBlocking {
        val fakeFood = Food(
            foodId = 100,
            title = "Test ",
            image = "https:image",
            description = "Test Desc",
            likeCount = 100 * 2 / 10,
            time = 10 * 100,
            vegetarian = false,
            vegan = false,
            veryHealthy = true,
            dairyFree = false,
            cheap = true,
            glutenFree = true,
            ingredients = emptyList(),
            analyzedIns = emptyList()
        )
        saveFavoriteFoodUseCase.invoke(fakeFood)
        val result = getFavoriteFoodByIdUseCase.invoke(fakeFood.foodId).first()
        assertThat(result).isEqualTo(fakeFood)
    }

    @Test
    fun `Get all favorite foods, returns not empty list`() = runBlocking {
        val result = getFavoriteFoodsUseCase.invoke(Unit).first()
        assertThat(result).isNotEmpty()
    }

    @Test
    fun `Delete favorite food by id`() = runBlocking {
        deleteFavoriteFoodUseCase.invoke(11)
        val result = getFavoriteFoodsUseCase.invoke(Unit).first()
        result.forEach {
            assertThat(it.foodId).isNotEqualTo(11)
        }
    }
    @Test
    fun `Clear favorite food list`() = runBlocking {
        deleteAllFavoriteFoodsUseCase.invoke(Unit)
        val result = getFavoriteFoodsUseCase.invoke(Unit).first()
        assertThat(result).isEmpty()
    }
}