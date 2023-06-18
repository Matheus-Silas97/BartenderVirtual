package com.bartender.bartendervirtual.common.navigation

import androidx.annotation.StringRes
import com.bartender.bartendervirtual.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val title: Int,
    val icon: Int
) {
    object HomeScreen : BottomNavigationScreens(
        route = RoutesNavigation.HOME_SCREEN, title = R.string.home,
        icon = R.drawable.ic_drink
    )

    object FavoriteScreen : BottomNavigationScreens(
        route = RoutesNavigation.FAVORITE_SCREEN, title = R.string.favorites,
        icon = R.drawable.ic_favorite
    )
}