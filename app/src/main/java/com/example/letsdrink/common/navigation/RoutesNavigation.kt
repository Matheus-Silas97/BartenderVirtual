package com.example.letsdrink.common.navigation

object RoutesNavigation {
    const val HOME_SCREEN = "home_screen"
    const val DRINKS_SCREEN = "drinks_screen"
    const val FAVORITE_SCREEN = "favorite_screen"
    const val INGREDIENTS_DETAILS_SCREEN = "ingredients_details_screen"
    const val DETAILS_DRINKS_SCREEN = "drinks_details_screen"
}

object RoutesArguments {
    const val INGREDIENT_ID = "ingredient_id"
    const val DRINK_ID = "drink_id"
    const val DRINK_NAME = "drink_name"
    const val CATEGORY_ID = "category_id"
    const val CATEGORY_NAME = "category_name"
}

//object RouterNavigation {
//    const val CATEGORY_ID = "category_id"
//    const val CATEGORY_NAME = "category_name"
//    object DrinkRouter: Router by RouterImpl(
//        id = "DRINKS_SCREEN",
//        popBackStack = false,
//        arguments = listOf(CATEGORY_ID, CATEGORY_NAME)
//    )
//}