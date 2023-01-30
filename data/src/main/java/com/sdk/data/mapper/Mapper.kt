package com.sdk.data.mapper

import com.sdk.domain.model.Food
import com.sdk.data.remote.model.Result
import java.util.*

fun Result.toFood(): Food {
    return Food(
        id = id,
        title = title,
        image = image,
        description = summary,
        isVegan = vegan,
        likeCount = Random().nextInt(1000),// we use random like count because food like incoming only zero from backend
        time = readyInMinutes
    )
}