package com.example.letsdrink.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object DrinksScreen : BottomNavigationScreens(
        route = "DrinksScreen", title = "Drinks",
        icon = Icons.Default.Home
    )

    object Screen2 : BottomNavigationScreens(
        route = "screen2", title = "Home",
        icon = Icons.Default.Home
    )

    object Screen3 : BottomNavigationScreens(
        route = "screen3", title = "Home",
        icon = Icons.Default.Home
    )
}