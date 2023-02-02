package com.sdk.domain.use_case.local.data_store

import com.sdk.domain.repository.LocalRepository
import com.sdk.domain.use_case.base.BaseUseCase
import javax.inject.Inject

typealias SaveThemeBaseUseCase = BaseUseCase<Int,Unit>

class SaveThemeUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveThemeBaseUseCase {
    override suspend fun invoke(parameter: Int) {
        repository.saveTheme(parameter)
    }
}