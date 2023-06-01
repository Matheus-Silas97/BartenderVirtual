package com.example.letsdrink.core.navigation

import androidx.annotation.StringRes
import com.example.letsdrink.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val title: Int,
    val icon: Int
) {
    object DrinksScreen : BottomNavigationScreens(
        route = RoutesNavigation.DRINKS_SCREEN, title = R.string.drinks,
        icon = R.drawable.ic_drink
    )

    object FavoriteScreen : BottomNavigationScreens(
        route = RoutesNavigation.FAVORITE_SCREEN, title = R.string.favorites,
        icon = R.drawable.ic_favorite
    )
}