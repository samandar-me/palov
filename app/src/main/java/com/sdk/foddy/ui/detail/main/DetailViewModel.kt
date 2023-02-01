package com.sdk.foddy.ui.detail.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.use_case.base.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: AllUseCases,
) : ViewModel() {
    private val _isFoodSaved: MutableState<Boolean> = mutableStateOf(false)
    val isFoodSaved: State<Boolean> get() = _isFoodSaved

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnCheckFood -> {
                viewModelScope.launch {
                    useCase.getFavoriteFoodByIdUseCase(event.id).collectLatest {
                        _isFoodSaved.value = it != null
                    }
                }
            }
            is DetailEvent.OnUpdateFood -> {
                viewModelScope.launch {
                    if (!_isFoodSaved.value) {
                        useCase.saveFavoriteFoodUseCase(event.food)
                    } else {
                        useCase.deleteFavoriteFoodUseCase(event.food.foodId)
                    }
                }
            }
        }
    }
}