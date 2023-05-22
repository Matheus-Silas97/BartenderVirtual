package com.example.letsdrink.core.navigation

import com.example.letsdrink.R

sealed class BottomNavigationScreens(
    val route: String,
    val title: String,
    val icon: Int
) {
    object DrinksScreen : BottomNavigationScreens(
        route = RoutesNavigation.DRINKS_SCREEN, title = "Bebidas",
        icon = R.drawable.ic_drink
    )

    object FavoriteScreen : BottomNavigationScreens(
        route = RoutesNavigation.FAVORITE_SCREEN, title = "Favoritos",
        icon = R.drawable.ic_favorite
    )

    object Screen3 : BottomNavigationScreens(
        route = "screen3", title = "Home",
        icon = R.drawable.ic_drink
    )
}