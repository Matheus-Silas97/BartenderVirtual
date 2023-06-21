package com.bartender.bartendervirtual.common.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.bartender.bartendervirtual.common.navigation.BottomBarState.GONE

enum class BottomBarState() {
    VISIBLE,
    GONE
}

val LocalBottomBarState = staticCompositionLocalOf {
    mutableStateOf(GONE)
}