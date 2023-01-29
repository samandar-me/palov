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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val useCases: AllUseCases,
    private val helper: NetworkHelper
) : ViewModel() {
    private val _state: MutableState<RecipesState> = mutableStateOf(RecipesState())
    val state: State<RecipesState> get() = _state

    init {
        getRecipes()
        getFoodState()
    }

    private fun getFoodState() {
        viewModelScope.launch {
            useCases.getFoodTypeUseCase.invoke(Unit).collectLatest {
                _state.value = _state.value.copy(foodType = it)
            }
        }
    }

    fun onEvent(event: RecipeEvent) {
        when (event) {
            is RecipeEvent.OnSearchFood -> {
                viewModelScope.launch {
                    useCases.getFoodTypeUseCase.invoke(Unit).collectLatest {
                        getRecipes(event.query)
                    }
                }
            }
            is RecipeEvent.OnSaveFoodType -> {
                viewModelScope.launch {
                    useCases.saveFoodTypeUseCase(event.foodType)
                }
            }
            is RecipeEvent.OnApplyClicked -> {
                viewModelScope.launch {
                    useCases.getFoodTypeUseCase.invoke(Unit).collectLatest {
                        getRecipes()
                    }
                }
            }
        }
    }

    private fun getRecipes(query: String? = null) {
        println("@@@${getQueries(null)}")
        if (helper.isNetworkConnected()) {
            viewModelScope.launch {
                useCases.getAllRecipesUseCase(
                    getQueries(query = query)
                ).collectLatest { response ->
                    when (response) {
                        is MyResult.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
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
                }
            }
        } else {
            _state.value = _state.value.copy(isLoading = false, error = "No internet connection")
        }
    }

    private fun getQueries(query: String?): HashMap<String, String> {
        val map = HashMap<String, String>()
        query?.let {
            map["query"] = it
        }
        map["number"] = "20"
        map["apiKey"] = Constants.API_KEY2
        map["addRecipeInformation"] = "true"
        map["fillIngredients"] = "true"
        map["type"] = _state.value.foodType.mType.lowercase()
        map["diet"] = _state.value.foodType.dType.lowercase()
        return map
    }

    private fun searchQueries(query: String): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["query"] = query
        map["number"] = "20"
        map["apiKey"] = Constants.API_KEY
        map["addRecipeInformation"] = "true"
        map["fillIngredients"] = "true"
        map["type"] = "main course"
        map["diet"] = "gluten free"
        return map
    }
}