package com.sdk.domain.model

data class Food(
    val image: String,
    val description: String,
    val title: String,
    val likeCount: Int,
    val time: Int,
    val isVegan: Boolean
)
