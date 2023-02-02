package com.sdk.foddy.ui.bottom.settings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.use_case.base.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: AllUseCases
) : ViewModel() {
    private val _themeState: MutableStateFlow<Int> = MutableStateFlow(0)
    val themeState: StateFlow<Int> get() = _themeState

    init {
        viewModelScope.launch {
            useCases.getThemeUseCase(Unit).collectLatest { theme ->
                _themeState.update {
                    theme
                }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        if (event is SettingsEvent.OnChangeTheme) {
            viewModelScope.launch {
                useCases.saveThemeUseCase(event.index)
            }
        }
    }
}