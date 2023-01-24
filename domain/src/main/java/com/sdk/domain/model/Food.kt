package com.sdk.domain.model

data class Food(
    val title: String,
    val image: String,
    val description: String,
    val isVegan: Boolean,
    val likeCount: Int,
    val time: String
)
