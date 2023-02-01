package com.sdk.foddy.ui.detail.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sdk.domain.model.FoodIngredient
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.IngredientItem
import com.sdk.foddy.ui.theme.DescColor
import com.sdk.foddy.ui.theme.PurpleGrey40

@Composable
fun IngredientsScreen(
    ingredients: List<FoodIngredient>?
) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp), modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer
            )
    ) {
        ingredients?.let {
            items(it) { item ->
                IngredientItem(ingredient = item)
            }
        }
    }
}