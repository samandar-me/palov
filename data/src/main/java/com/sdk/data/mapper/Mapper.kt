package com.sdk.data.mapper

import com.sdk.domain.model.Food
import com.sdk.data.remote.model.Result

fun Result.toFood(): Food {
    return Food(
        id = id,
        title = title,
        image = image,
        description = summary,
        isVegan = vegan,
        likeCount = likes,
        time = readyInMinutes
    )
}