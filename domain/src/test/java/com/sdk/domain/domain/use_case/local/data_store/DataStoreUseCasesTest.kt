package com.sdk.domain.domain.use_case.local.data_store

import com.google.common.truth.Truth.assertThat
import com.sdk.domain.data.repository.FakeLocalRepository
import com.sdk.domain.model.FoodType
import com.sdk.domain.use_case.local.data_store.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DataStoreUseCasesTest {

    private lateinit var getUserVisitingUseCase: GetUserVisitingUseCase
    private lateinit var saveUserVisitingUseCase: SaveUserVisitingUseCase
    private lateinit var saveFoodTypeUseCase: SaveFoodTypeUseCase
    private lateinit var getFoodTypeUseCase: GetFoodTypeUseCase
    private lateinit var saveThemeUseCase: SaveThemeUseCase
    private lateinit var getThemeUseCase: GetThemeUseCase
    private lateinit var fakeLocalRepository: FakeLocalRepository

    @Before
    fun setUp() {
        fakeLocalRepository = FakeLocalRepository()
        getUserVisitingUseCase = GetUserVisitingUseCase(fakeLocalRepository)
        saveUserVisitingUseCase = SaveUserVisitingUseCase(fakeLocalRepository)
        saveFoodTypeUseCase = SaveFoodTypeUseCase(fakeLocalRepository)
        getFoodTypeUseCase = GetFoodTypeUseCase(fakeLocalRepository)
        getThemeUseCase = GetThemeUseCase(fakeLocalRepository)
        saveThemeUseCase = SaveThemeUseCase(fakeLocalRepository)
    }

    @Test
    fun `By default user visiting returns false`() = runBlocking {
        val res = getUserVisitingUseCase(Unit).first()
        assertThat(res).isFalse()
    }
    @Test
    fun `When save user visiting returns true`() = runBlocking {
        saveUserVisitingUseCase(true)
        val res = getUserVisitingUseCase(Unit).first()
        assertThat(res).isTrue()
    }

    @Test
    fun `By default food type returns empty food`() = runBlocking {
        val res = getFoodTypeUseCase(Unit).first()
        assertThat(res).isEqualTo(FoodType())
    }
    @Test
    fun `Save food type and check`() = runBlocking {
        saveFoodTypeUseCase(FoodType(mIndex = 2, mType = "Pasta", dIndex = 3, dType = "D"))
        val res = getFoodTypeUseCase(Unit).first()
        assertThat(res.mType).contains("P")
    }

    @Test
    fun `By default theme index shouldn't returns null`() = runBlocking {
        val res = getThemeUseCase(Unit).first()
        assertThat(res).isNotNull()
    }

    @Test
    fun `By default theme index returns zero`() = runBlocking {
        val res = getThemeUseCase(Unit).first()
        assertThat(res).isEqualTo(0)
    }
    @Test
    fun `Save theme index and get it`() = runBlocking {
        saveThemeUseCase(2)
        val res = getThemeUseCase(Unit).first()
        assertThat(res).isLessThan(3)
    }
}