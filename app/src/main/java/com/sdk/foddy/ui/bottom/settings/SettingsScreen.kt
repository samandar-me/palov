package com.sdk.foddy.ui.bottom.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sdk.foddy.ui.component.ThemeItem
import com.sdk.foddy.ui.theme.AppFont
import com.sdk.foddy.ui.theme.ItimFont

@Composable
fun SettingsScreen() {
    val themeList = listOf("Automatic", "Light", "Dark")
    val viewModel: SettingsViewModel = hiltViewModel()
    val state = viewModel.themeState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer
            )
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Settings",
                    fontFamily = AppFont,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
        )
        LazyColumn(
            contentPadding = PaddingValues(10.dp)
        ) {
            item {
                Text(
                    text = "Theme",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontFamily = ItimFont
                )
            }
            itemsIndexed(themeList) { index, item ->
                ThemeItem(isEnable = state == index, title = item) {
                    viewModel.onEvent(SettingsEvent.OnChangeTheme(index))
                }
            }
        }
    }
}