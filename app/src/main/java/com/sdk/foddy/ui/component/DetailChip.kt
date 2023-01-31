package com.sdk.foddy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.sdk.foddy.R

@Composable
fun DetailChip(
    modifier: Modifier = Modifier,
    text: String,
    isTrue: Boolean,
) {
    Row(
        modifier = modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_check_circle_24),
            contentDescription = "",
            tint = if (isTrue) Color.Green else Color.Black
        )
        AppText(text = text, size = 15, color = MaterialTheme.colorScheme.onSecondary)
    }
}