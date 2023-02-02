package com.sdk.foddy.ui.bottom.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.use_case.base.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCases: AllUseCases,
) : ViewModel() {
    private val _state: MutableState<FavoriteState> = mutableStateOf(FavoriteState())
    val state: State<FavoriteState> get() = _state

    init {
        getAllFavoriteFoods()
    }

    fun onEvent(event: FavoriteEvent) {
        if (event is FavoriteEvent.OnDeleteAllFavoriteFoods) {
            viewModelScope.launch(Dispatchers.IO) {
                useCases.deleteAllFavoriteFoodsUseCase(Unit)
            }
        }
    }

    private fun getAllFavoriteFoods() {
        viewModelScope.launch {
            useCases.getFavoriteFoodsUseCase(Unit).collectLatest {
                _state.value = _state.value.copy(isLoading = true)
                delay(200L)
                _state.value = _state.value.copy(isLoading = false, foodList = it)
            }
        }
    }
}