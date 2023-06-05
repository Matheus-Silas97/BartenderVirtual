package com.example.letsdrink.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.letsdrink.presentation.drink_details.DrinkDetailsScreen
import com.example.letsdrink.presentation.drinks.DrinksScreen
import com.example.letsdrink.presentation.favorite.FavoriteDrinksScreen
import com.example.letsdrink.presentation.home.HomeScreen
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsScreen

@Composable
fun NavigationNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.HomeScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = BottomNavigationScreens.HomeScreen.route) {
            HomeScreen(goToDrinkScreen = { id, categoryName ->
                navController.navigate(route = "${RoutesNavigation.DRINKS_SCREEN}/$id/$categoryName")
            })
        }

        composable(route = BottomNavigationScreens.FavoriteScreen.route) {
            FavoriteDrinksScreen(
                goToDetailsDrinkScreen = { id ->
                    navController.navigate(route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/${id}")
                }
            )
        }

        composable(
            route = "${RoutesNavigation.DRINKS_SCREEN}/{category_id}/{category_name}",
            arguments = listOf(
                navArgument("category_id") { type = NavType.LongType },
                navArgument("category_name") { type = NavType.StringType })
        ) { backStackEntry ->
            DrinksScreen(
                categoryId = backStackEntry.arguments?.getLong("category_id") ?: 0L,
                categoryName = backStackEntry.arguments?.getString("category_name", "") ?: "",
                goToDetailsDrinksScreen = { id ->
                    navController.navigate(route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/${id}")
                },
                backPressed = {
                    navController.popBackStack()
                })
        }

        composable(
            route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/{drink_id}",
            arguments = listOf(navArgument("drink_id") { type = NavType.LongType })
        ) { backStackEntry ->
            DrinkDetailsScreen(
                drinkId = backStackEntry.arguments?.getLong("drink_id") ?: 0L,
                backStack = { navController.popBackStack() }
            )
        }
        composable(route = "${RoutesNavigation.INGREDIENTS_DETAILS_SCREEN}/{ingredient_id}",
            arguments = listOf(
                navArgument("ingredient_id") { type = NavType.LongType }
            )) { backStackEntry ->
            IngredientsDetailsScreen(
                ingredientId = backStackEntry.arguments?.getLong("ingredient_id") ?: 0L,
                backStack = { navController.popBackStack() },
                goToDetailsDrinkScreen = { id ->
                    navController.navigate(route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/${id}")
                }
            )
        }
    }
}