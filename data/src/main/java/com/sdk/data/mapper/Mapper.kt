package com.sdk.data.mapper

import com.sdk.data.remote.model.ExtendedIngredient
import com.sdk.domain.model.Food
import com.sdk.data.remote.model.Result
import com.sdk.domain.model.Ingredient
import java.util.*

fun Result.toFood(): Food {
    return Food(
        id = id,
        title = title,
        image = image,
        description = summary,
        isVegan = vegan,
        likeCount = Random().nextInt(1000),// we use random like count because food like incoming only zero from backend
        time = readyInMinutes,
        vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy,
        cheap = cheap,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        ingredients = extendedIngredients.map { it.toIngredient() }
    )
}

fun ExtendedIngredient.toIngredient(): Ingredient {
    return Ingredient(
        id = id,
        aisle = aisle,
        consistency = consistency,
        image = image,
        meta = meta,
        name = name,
        nameClean = nameClean,
        original = original,
        originalName = originalName,
        unit = unit,
        amount = amount
    )
}