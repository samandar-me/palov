package com.sdk.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("id")
    val foodId: Int,
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
    val ingredients: List<FoodIngredient>,
    val analyzedIns: List<AnalyzedInstructions>
) : Parcelable

@Parcelize
data class FoodIngredient(
    val aisle: String?,
    val amount: Double,
    val consistency: String,
    val id: Int,
    val ingImage: String?,
    val meta: List<String>,
    val name: String?,
    val nameClean: String?,
    val original: String,
    val originalName: String,
    val unit: String
) : Parcelable

@Parcelize
data class InsStep(
    val ingredients: List<InsIngredient>,
    val number: Int,
    val step: String
) : Parcelable

@Parcelize
data class AnalyzedInstructions(
    val steps: List<InsStep>
) : Parcelable

@Parcelize
data class InsIngredient(
    val id: Int,
    val image: String,
    val name: String
) : Parcelable
