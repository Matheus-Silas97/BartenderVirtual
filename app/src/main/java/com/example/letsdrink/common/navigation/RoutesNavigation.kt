package com.example.letsdrink.common.navigation

import com.example.letsdrink.Router
import com.example.letsdrink.RouterImpl

object RoutesNavigation {
    const val HOME_SCREEN = "HOME_SCREEN"
    const val DRINKS_SCREEN = "DRINKS_SCREEN"
    const val FAVORITE_SCREEN = "FAVORITE_SCREEN"
    const val INGREDIENTS_DETAILS_SCREEN = "INGREDIENTS_DETAILS_SCREEN"
    const val DETAILS_DRINKS_SCREEN = "DETAILS_DRINKS_SCREEN"
}

object RouterNavigation {
    const val CATEGORY_ID = "category_id"
    const val CATEGORY_NAME = "category_name"
    object DrinkRouter: Router by RouterImpl(
        id = "DRINKS_SCREEN",
        popBackStack = false,
        arguments = listOf(CATEGORY_ID, CATEGORY_NAME)
    )
}