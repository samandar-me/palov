package com.sdk.foddy.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.sdk.foddy.ui.theme.AppFont

@Composable
fun AppText(
    text: String,
    size: Int,
    color: Color,
    maxLine: Int
) {
    Text(
        text = text,
        fontSize = (size).sp,
        maxLines = maxLine,
        color = color,
        fontFamily = AppFont
    )
}