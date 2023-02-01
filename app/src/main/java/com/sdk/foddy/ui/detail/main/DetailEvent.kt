package com.sdk.foddy.ui.detail.main

import com.sdk.domain.model.Food

sealed class DetailEvent {
    data class OnCheckFood(val id: Int): DetailEvent()
    data class OnUpdateFood(val food: Food): DetailEvent()
}