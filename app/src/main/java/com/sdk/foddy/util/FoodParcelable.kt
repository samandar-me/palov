package com.sdk.foddy.util

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.sdk.domain.model.Food

class AssetParamType : NavType<Food>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Food? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Food {
        return Gson().fromJson(value, Food::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Food) {
        bundle.putParcelable(key, value)
    }
}