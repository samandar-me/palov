package com.sdk.domain.use_case.local.data_store

import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetThemeBaseUseCase = BaseUseCase<Unit, Flow<Int>>

class GetThemeUseCase @Inject constructor(
    private val repository: LocalRepository
) : GetThemeBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<Int> {
        return repository.getTheme()
    }
}