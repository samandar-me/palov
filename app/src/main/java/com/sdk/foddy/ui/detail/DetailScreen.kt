package com.sdk.foddy.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sdk.domain.model.Food
import com.sdk.foddy.R
import com.sdk.foddy.ui.component.BackgroundImage
import com.sdk.foddy.ui.theme.AppFont

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    food: Food?
) {
    BackgroundImage(image = R.drawable.img)
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(text = "${food?.title}", color = MaterialTheme.colorScheme.onSecondary, fontFamily = AppFont, maxLines = 1)
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }
}