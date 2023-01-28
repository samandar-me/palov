package com.sdk.foddy.ui.bottom.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.BackgroundImage
import com.sdk.foddy.ui.theme.AppFont

@Composable
fun FavoriteScreen(navHostController: NavHostController) {
    BackgroundImage(image = R.drawable.img_2)
    Column {
        TopAppBar(
            title = {
                Text(text = "Favorite", fontFamily = AppFont,color = MaterialTheme.colorScheme.onSecondary)
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
        )
    }
}