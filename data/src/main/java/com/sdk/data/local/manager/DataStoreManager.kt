package com.sdk.data.local.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.sdk.domain.model.FoodType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("DishDataStore")

    companion object {
        val mIndex = intPreferencesKey("mIndex")
        val mType = stringPreferencesKey("mType")
        val dIndex = intPreferencesKey("dIndex")
        val dType = stringPreferencesKey("dType")

        val themIndex = intPreferencesKey("themeIndex")

        val isUserVisited = booleanPreferencesKey("isUserVisited")
    }

    suspend fun saveMealType(foodType: FoodType) {
        context.dataStore.edit {
            it[mIndex] = foodType.mIndex
            it[mType] = foodType.mType
            it[dIndex] = foodType.dIndex
            it[dType] = foodType.dType
        }
    }

    fun getFoodType() = context.dataStore.data.map {
        FoodType(
            mIndex = it[mIndex] ?: 0,
            mType = it[mType] ?: "Main Course",
            dIndex = it[dIndex] ?: 0,
            dType = it[dType] ?: "Gluten Free"
        )
    }

    suspend fun saveTheme(index: Int) {
        context.dataStore.edit {
            it[themIndex] = index
        }
    }

    fun getTheme(): Flow<Int> = context.dataStore.data.map {
        it[themIndex] ?: 0
    }

    suspend fun saveUserVisiting(boolean: Boolean) {
        context.dataStore.edit {
            it[isUserVisited] = boolean
        }
    }

    fun getUserVisiting(): Flow<Boolean> = context.dataStore.data.map {
        it[isUserVisited] ?: false
    }
}