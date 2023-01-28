package com.sdk.foddy.ui.bottom.settings

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
fun SettingsScreen(navHostController: NavHostController) {
    BackgroundImage(image = R.drawable.img_3)
    Column {
        TopAppBar(
            title = {
                Text(text = "Settings", fontFamily = AppFont, color = MaterialTheme.colorScheme.onSecondary)
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
        )
    }
}