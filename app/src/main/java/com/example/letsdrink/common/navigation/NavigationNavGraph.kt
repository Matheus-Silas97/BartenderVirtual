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
import com.example.letsdrink.common.navigation.RouterNavigation.CATEGORY_ID
import com.example.letsdrink.common.navigation.RouterNavigation.CATEGORY_NAME
import com.example.letsdrink.common.navigation.RouterNavigation.DrinkRouter
import com.example.letsdrink.presentation.drink_details.DrinkDetailsScreen
import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel
import com.example.letsdrink.presentation.drinks.DrinksScreen
import com.example.letsdrink.presentation.favorite.FavoriteDrinksScreen
import com.example.letsdrink.presentation.home.HomeScreen
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsScreen
import com.example.letsdrink.second
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.HomeScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = BottomNavigationScreens.HomeScreen.route) {
            HomeScreen(goToDrinkScreen = { id, categoryName ->
                navController.navigate(
                    route = DrinkRouter.address(listOf(id.toString(), categoryName))
                )
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
            route = DrinkRouter.router,
            arguments = listOf(
                navArgument(DrinkRouter.arguments!!.first()) { type = NavType.LongType },
                navArgument(DrinkRouter.arguments!!.second()) { type = NavType.StringType })
        ) { backStackEntry ->
            DrinksScreen(
                categoryId = backStackEntry.arguments?.getLong(DrinkRouter.arguments.first()) ?: 0L,
                categoryName = backStackEntry.arguments?.getString(DrinkRouter.arguments.second()).orEmpty(),
                goToDetailsDrinksScreen = { id ->
                    navController.navigate(route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/${id}")
                },
                backPressed = {
                    navController.popBackStack()
                })
        }

        composable(
            route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/{drink_id}",
            arguments = listOf(
                navArgument("drink_id") {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )
        ) { backStackEntry ->
            val drinkId = requireNotNull(backStackEntry.arguments?.getLong("drink_id"))
            val viewModel = getViewModel<DrinkDetailsViewModel>(
                parameters = { parametersOf(drinkId) }
            )
            DrinkDetailsScreen(
                backStack = { navController.popBackStack() },
                navigateNextScreen = { id ->
                    navController.navigate(route = "${RoutesNavigation.INGREDIENTS_DETAILS_SCREEN}/$id")
                },
                viewModel = viewModel,
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