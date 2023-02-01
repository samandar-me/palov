package com.sdk.data.local.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sdk.domain.model.AnalyzedInstructions


class AnalyzedInstructionsConverter {

    @TypeConverter
    fun fromList(mutableList: MutableList<AnalyzedInstructions>): String {
        val type = object : TypeToken<MutableList<AnalyzedInstructions>>() {}.type
        return Gson().toJson(mutableList, type)
    }
    @TypeConverter
    fun  toList(json: String): MutableList<AnalyzedInstructions> {
        val type = object : TypeToken<MutableList<AnalyzedInstructions>>() {}.type
        return Gson().fromJson(json, type)
    }
}