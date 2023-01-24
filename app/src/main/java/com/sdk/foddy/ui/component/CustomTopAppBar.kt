package com.sdk.foddy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.foddy.ui.theme.AppFont

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    isBackIconVisible: Boolean,
    isSearchIconVisible: Boolean,
    onBackClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    title: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isBackIconVisible) {
                IconButton(onClick = { onBackClicked() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
            Spacer(modifier = Modifier.width(13.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 20.sp,
                fontFamily = AppFont,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        if (isSearchIconVisible) {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
    }
}