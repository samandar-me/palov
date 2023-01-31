package com.sdk.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
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
    val glutenFree: Boolean,
    @SerializedName("extendedIngredients")
    val ingredients: List<Ingredient>
): Parcelable

@Parcelize
data class Ingredient(
    val aisle: String?,
    val amount: Double,
    val consistency: String,
    val id: Int,
    val image: String,
    val meta: List<String>,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val unit: String
): Parcelable
