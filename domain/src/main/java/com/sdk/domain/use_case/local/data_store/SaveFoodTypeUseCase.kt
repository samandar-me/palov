package com.sdk.domain.use_case.local.data_store

import com.sdk.domain.model.FoodType
import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias SaveFoodTypeBaseUseCase = BaseUseCase<FoodType, Unit>

class SaveFoodTypeUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveFoodTypeBaseUseCase {
    override suspend fun invoke(parameter: FoodType) {
        repository.saveFoodType(parameter)
    }
}