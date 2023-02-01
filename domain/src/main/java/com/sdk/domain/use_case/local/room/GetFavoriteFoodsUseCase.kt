package com.sdk.domain.use_case.local.room

import com.sdk.domain.model.Food
import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFavoriteFoodsBaseUseCase = BaseUseCase<Unit, Flow<List<Food>>>

class GetFavoriteFoodsUseCase @Inject constructor(
    private val repository: LocalRepository
): GetFavoriteFoodsBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<List<Food>> {
        return repository.getFoodList()
    }
}