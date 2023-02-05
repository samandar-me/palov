package com.sdk.domain.domain.use_case.local.data_store

import com.sdk.domain.data.repository.FakeLocalRepository
import com.sdk.domain.use_case.local.data_store.GetUserVisitingUseCase
import com.sdk.domain.use_case.local.data_store.SaveUserVisitingUseCase
import org.junit.Before

class DataStoreUseCasesTest {

    private lateinit var getUserVisitingUseCase: GetUserVisitingUseCase
    private lateinit var saveUserVisitingUseCase: SaveUserVisitingUseCase
    private lateinit var fakeLocalRepository: FakeLocalRepository

    @Before
    fun setUp() {
        fakeLocalRepository = FakeLocalRepository()
        getUserVisitingUseCase = GetUserVisitingUseCase(fakeLocalRepository)
        saveUserVisitingUseCase = SaveUserVisitingUseCase(fakeLocalRepository)
    }
}