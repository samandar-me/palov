package com.sdk.foddy.ui.bottom.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sdk.foddy.ui.theme.AppFont

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        TopAppBar(
            title = {
                Text(text = "Settings", fontFamily = AppFont, color = MaterialTheme.colorScheme.onSecondary)
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
        )
        Text(text = "Test")
    }
}