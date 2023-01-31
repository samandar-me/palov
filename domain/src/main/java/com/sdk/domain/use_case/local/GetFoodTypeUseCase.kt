package com.sdk.domain.use_case.local

import com.sdk.domain.model.FoodType
import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFoodTypeBaseUseCase = BaseUseCase<Unit,Flow<FoodType>>

class GetFoodTypeUseCase @Inject constructor(
    private val repository: LocalRepository
): GetFoodTypeBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<FoodType> {
        return repository.getFoodType()
    }
}