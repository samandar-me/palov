package com.sdk.domain.use_case.local.room

import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias DeleteFavoriteFoodBaseUseCase = BaseUseCase<Int,Unit>

class DeleteFavoriteFoodUseCase @Inject constructor(
    private val repository: LocalRepository
): DeleteFavoriteFoodBaseUseCase {
    override suspend fun invoke(parameter: Int) {
        repository.deleteFood(parameter)
    }
}