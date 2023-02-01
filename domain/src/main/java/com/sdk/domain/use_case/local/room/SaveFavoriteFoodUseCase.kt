package com.sdk.domain.use_case.local.room

import com.sdk.domain.model.Food
import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias SaveFavoriteFoodBaseUseCase = BaseUseCase<Food,Unit>

class SaveFavoriteFoodUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveFavoriteFoodBaseUseCase {
    override suspend fun invoke(parameter: Food) {
        repository.saveFavoriteFood(parameter)
    }
}