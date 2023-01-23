package com.sdk.foddy.ui.bottom.recipes

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.BackgroundImage
import com.sdk.foddy.util.Graph

@Composable
fun RecipesScreen(navHostController: NavHostController) {
    BackgroundImage(image = R.drawable.img_1)
    Button(onClick = { navHostController.navigate(Graph.DETAIL)}) {

    }
}