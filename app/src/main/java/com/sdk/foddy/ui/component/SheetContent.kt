package com.sdk.foddy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sdk.data.util.Constants
import com.sdk.domain.model.FoodType

@Composable
fun SheetContent(
    onApplyClicked: () -> Unit,
    onChipClicked: (FoodType) -> Unit,
    foodState: FoodType
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .height(350.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AppText(
            modifier = Modifier.padding(start = 15.dp),
            text = "Meal Type",
            size = 20,
            color = MaterialTheme.colorScheme.onSecondary
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(Constants.mealTypes()) { index, item ->
                Chip(
                    isSelected = foodState.mIndex == index,
                    onClicked = {
                        onChipClicked(FoodType(
                            mIndex = index,
                            mType = item,
                            dIndex = foodState.dIndex,
                            dType = foodState.dType
                        ))
                    },
                    text = item
                )
            }
        }
        AppText(
            modifier = Modifier.padding(start = 15.dp),
            text = "Diet Type",
            size = 20,
            color = MaterialTheme.colorScheme.onSecondary
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(Constants.dietTypes()) { index, item ->
                Chip(
                    isSelected = foodState.dIndex == index,
                    onClicked = {
                        onChipClicked(FoodType(
                            dIndex = index,
                            dType = item,
                            mIndex = foodState.mIndex,
                            mType = foodState.mType
                        ))
                    },
                    text = item
                )
            }
        }
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 20.dp),
            onClick = {
                onApplyClicked()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        ) {
            AppText(
                text = "Apply",
                size = 18,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}