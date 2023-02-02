package com.sdk.domain.use_case.local.room

import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias DeleteAllFavoriteFoodsBaseUseCase = BaseUseCase<Unit, Unit>

class DeleteAllFavoriteFoodsUseCase @Inject constructor(
    private val repository: LocalRepository
) : DeleteAllFavoriteFoodsBaseUseCase {
    override suspend fun invoke(parameter: Unit) {
        repository.deleteAllFavoriteFoods()
    }
}