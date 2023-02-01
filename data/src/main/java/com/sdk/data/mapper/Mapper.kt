package com.sdk.data.mapper

import com.sdk.data.remote.model.*
import com.sdk.domain.model.*
import java.util.*

fun Result.toFood(): Food {
    return Food(
        id = 0,
        foodId = foodId,
        title = title,
        image = image,
        description = summary,
        likeCount = Random().nextInt(1000),// we use random like count because food like incoming only zero from backend
        time = readyInMinutes,
        vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy,
        cheap = cheap,
        glutenFree = glutenFree,
        dairyFree = dairyFree,
        ingredients = extendedIngredients.map { it.toIngredient() },
        analyzedIns = analyzedInstructions.map { it.toAnalyzed() }
    )
}

fun ExtendedIngredient.toIngredient(): FoodIngredient {
    return FoodIngredient(
        id = id,
        aisle = aisle,
        consistency = consistency,
        ingImage = image,
        name = name,
        nameClean = nameClean,
        original = original,
        originalName = originalName,
        unit = unit,
        amount = amount,
    )
}

fun AnalyzedInstructionsDTOItem.toAnalyzed(): AnalyzedInstructions {
    return AnalyzedInstructions(
        steps = steps.map { it.toStep() }
    )
}

fun Step.toStep(): InsStep {
    return InsStep(
        ingredients = ingredients.map { it.toInsIngredient() },
        number = number,
        step = step
    )
}
fun Ingredient.toInsIngredient(): InsIngredient {
    return InsIngredient(
        id = id,
        name = name,
        image = image
    )
}