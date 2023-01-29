package com.sdk.foddy.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.foddy.ui.theme.ItimFont

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClicked: () -> Unit,
    text: String,
) {
    Surface(
        modifier = modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onClicked()
            },
        color = if (isSelected) MaterialTheme.colorScheme.onTertiaryContainer
        else Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
                Spacer(modifier = Modifier.width(7.dp))
            }
            Text(
                text = text,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = ItimFont
            )
        }
    }
}