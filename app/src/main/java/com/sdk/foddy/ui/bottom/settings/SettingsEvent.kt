package com.sdk.foddy.ui.bottom.settings

sealed class SettingsEvent {
    data class OnChangeTheme(val index: Int): SettingsEvent()
}