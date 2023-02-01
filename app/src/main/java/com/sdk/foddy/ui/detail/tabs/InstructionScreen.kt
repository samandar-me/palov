package com.sdk.foddy.ui.detail.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sdk.domain.model.InsStep
import com.sdk.foddy.ui.component.InstructionItem
import com.sdk.foddy.ui.theme.DescColor

@Composable
fun InstructionsScreen(
    steps: List<InsStep>?,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onTertiary)
    ) {
        steps?.let {
            items(it) { item ->
                InstructionItem(insStep = item)
            }
        }
    }
}