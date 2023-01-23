package com.sdk.data.remote.model

data class FoodResponse(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)