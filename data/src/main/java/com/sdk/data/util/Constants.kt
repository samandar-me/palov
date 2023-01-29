package com.sdk.data.util

object Constants  {
    const val API_KEY = "ddb2169c7f044f49a4b3d3714d627ecb"
    const val API_KEY2 = "754b79e5a34b441a81e0e83a0e5286c3"
    const val BASE_URL = "https://api.spoonacular.com/"
    const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

    fun dietTypes() = listOf(
        "Gluten Free",
        "Ketogenic",
        "Vegetarian",
        "Lacto-Vegetarian",
        "Vegan",
        "Pescetarian",
        "Paleo",
        "Primal"
    )
    fun mealTypes() = listOf(
        "Main course",
        "Side dish",
        "Dessert",
        "Appetizer",
        "Salad",
        "Breakfast",
        "Soup",
        "Beverage",
        "Sauce",
        "Marinade",
        "Fingerfood",
        "Snack",
        "Drink"
    )
}