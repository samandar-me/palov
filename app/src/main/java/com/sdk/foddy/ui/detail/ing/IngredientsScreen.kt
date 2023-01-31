package com.sdk.foddy.ui.detail.ing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sdk.domain.model.Ingredient
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.BackgroundImage
import com.sdk.foddy.ui.component.IngredientItem
import com.sdk.foddy.ui.theme.DescColor

@Composable
fun IngredientsScreen(
    ingredients: List<Ingredient>?
) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp), modifier = Modifier
            .fillMaxSize()
            .background(
                DescColor
            )
    ) {
        ingredients?.let {
            items(it) { item ->
                IngredientItem(ingredient = item)
            }
        }
    }
}