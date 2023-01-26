package com.sdk.foddy.ui.bottom.recipes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.data.util.Constants
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.MyResult
import com.sdk.foddy.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val useCases: AllUseCases,
    private val helper: NetworkHelper,
) : ViewModel() {
    private val _state: MutableState<RecipesState> = mutableStateOf(RecipesState())
    val state: State<RecipesState> get() = _state

    init {
        getRandomRecipes()
    }

    private fun getRandomRecipes() {
        if (helper.isNetworkConnected()) {
            viewModelScope.launch {
                useCases.getAllRecipesUseCase(getQueries()).collect { response ->
                    when (response) {
                        is MyResult.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                            delay(1000L)
                        }
                        is MyResult.Error -> {
                            _state.value =
                                _state.value.copy(isLoading = false, error = response.message)
                        }
                        is MyResult.Success -> {
                            _state.value =
                                _state.value.copy(isLoading = false, success = response.data)
                        }
                    }
                    println("@@@vm${state.value}")
                }
            }
        } else {
            _state.value = _state.value.copy(isLoading = false, error = "No internet connection")
        }
    }
    private fun getQueries(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["number"] = "20"
        map["apiKey"] = Constants.API_KEY
        map["addRecipeInformation"] = "true"
        map["fillIngredients"] = "true"
        map["type"] = "main course"
        map["diet"] = "gluten free"
        return map
    }
}