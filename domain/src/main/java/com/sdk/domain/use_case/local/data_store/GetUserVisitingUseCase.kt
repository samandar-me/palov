package com.sdk.domain.use_case.local.data_store

import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetUserVisitingBaseUseCase = BaseUseCase<Unit, Flow<Boolean>>

class GetUserVisitingUseCase @Inject constructor(
    private val repository: LocalRepository
) : GetUserVisitingBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<Boolean> {
        return repository.getUserVisiting()
    }
}