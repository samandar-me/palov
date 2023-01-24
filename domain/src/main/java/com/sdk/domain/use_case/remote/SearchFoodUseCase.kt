package com.sdk.domain.use_case.remote

import com.sdk.domain.model.Food
import com.sdk.domain.repository.RemoteRepository
import com.sdk.domain.use_case.base.BaseUseCase
import com.sdk.domain.util.MyResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias SearchFoodBaseUseCase = BaseUseCase<Map<String, String>, Flow<MyResult<List<Food>>>>

class SearchFoodUseCase @Inject constructor(
    private val repository: RemoteRepository
): SearchFoodBaseUseCase  {
    override suspend fun invoke(parameter: Map<String, String>): Flow<MyResult<List<Food>>> {
        return repository.searchRecipe(parameter)
    }
}