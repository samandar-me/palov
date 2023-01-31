package com.sdk.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val isVegan: Boolean,
    val likeCount: Int,
    val time: Int,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val dairyFree: Boolean,
    val cheap: Boolean,
    val glutenFree: Boolean
): Parcelable
