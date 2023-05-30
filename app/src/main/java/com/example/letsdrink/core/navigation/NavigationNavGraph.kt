package com.example.letsdrink.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.letsdrink.presentation.drink_details.DrinkDetailsScreen
import com.example.letsdrink.presentation.drinks.DrinksScreen
import com.example.letsdrink.presentation.favorite.FavoriteDrinks
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsScreen

@Composable
fun NavigationNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.DrinksScreen.route
    ) {
        composable(route = BottomNavigationScreens.DrinksScreen.route) { DrinksScreen(navController) }
        composable(route = BottomNavigationScreens.FavoriteScreen.route) {
            FavoriteDrinks(
                navController
            )
        }

        composable(
            route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/{drink_id}",
            arguments = listOf(navArgument("drink_id") { type = NavType.LongType })
        ) { backStackEntry ->
            DrinkDetailsScreen(
                drinkId = backStackEntry.arguments?.getLong("drink_id") ?: 0L,
                navController = navController
            )
        }
        composable(route = "${RoutesNavigation.INGREDIENTS_DETAILS_SCREEN}/{ingredient_id}",
            arguments = listOf(
                navArgument("ingredient_id") { type = NavType.LongType }
            )) { backStackEntry ->
            IngredientsDetailsScreen(
                ingredientId = backStackEntry.arguments?.getLong("ingredient_id") ?: 0L,
                navController = navController
            )
        }
    }
}