package com.sdk.domain.use_case.local.room

import com.sdk.domain.model.Food
import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFavoriteFoodByIdBaseUseCase = BaseUseCase<Int, Flow<Food?>>

class GetFavoriteFoodByIdUseCase @Inject constructor(
    private val repository: LocalRepository
): GetFavoriteFoodByIdBaseUseCase {
    override suspend fun invoke(parameter: Int): Flow<Food?> {
        return repository.getFoodById(parameter)
    }
}