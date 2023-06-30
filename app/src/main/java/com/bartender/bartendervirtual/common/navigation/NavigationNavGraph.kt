package com.bartender.bartendervirtual.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bartender.bartendervirtual.common.navigation.BottomBarState.GONE
import com.bartender.bartendervirtual.common.navigation.BottomBarState.VISIBLE
import com.bartender.bartendervirtual.common.navigation.BottomNavigationScreens.FavoriteScreen
import com.bartender.bartendervirtual.common.navigation.BottomNavigationScreens.HomeScreen
import com.bartender.bartendervirtual.common.navigation.RoutesArguments.CATEGORY_ID
import com.bartender.bartendervirtual.common.navigation.RoutesArguments.CATEGORY_NAME
import com.bartender.bartendervirtual.common.navigation.RoutesNavigation.DETAILS_DRINKS_SCREEN
import com.bartender.bartendervirtual.common.navigation.RoutesNavigation.DRINKS_SCREEN
import com.bartender.bartendervirtual.common.navigation.RoutesNavigation.INGREDIENTS_DETAILS_SCREEN
import com.bartender.bartendervirtual.common.navigation.RoutesNavigation.SPLASH_SCREEN
import com.bartender.bartendervirtual.presentation.drink_details.DrinkDetailsScreen
import com.bartender.bartendervirtual.presentation.drink_details.DrinkDetailsViewModel
import com.bartender.bartendervirtual.presentation.drinks.DrinksScreen
import com.bartender.bartendervirtual.presentation.favorite.FavoriteDrinksScreen
import com.bartender.bartendervirtual.presentation.home.HomeScreen
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsScreen
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsViewModel
import com.bartender.bartendervirtual.presentation.splash_screen.SplashScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = SPLASH_SCREEN) {
            LocalBottomBarState.current.value = GONE
            SplashScreen(goToHomeScreen = {
                navController.navigate(route = HomeScreen.route) {
                    popUpTo(route = SPLASH_SCREEN) { inclusive = true }
                }
            })
        }


        composable(route = HomeScreen.route) {
            LocalBottomBarState.current.value = VISIBLE
            HomeScreen(goToDrinkScreen = { id, categoryName ->
                navController.navigate(
                    route = "$DRINKS_SCREEN/$id/$categoryName"
                )
            }, goToDrinkDetails = { id ->
                navController.navigate(route = "$DETAILS_DRINKS_SCREEN/${id}")
            })
        }

        composable(route = FavoriteScreen.route) {
            FavoriteDrinksScreen(
                goToDetailsDrinkScreen = { id ->
                    navController.navigate(route = "$DETAILS_DRINKS_SCREEN/$id")
                }
            )
        }

        composable(
            route = "$DRINKS_SCREEN/{$CATEGORY_ID}/{$CATEGORY_NAME}",
            arguments = listOf(
                navArgument(name = CATEGORY_ID) {
                    type = NavType.LongType
                    defaultValue = 0L
                },
                navArgument(name = CATEGORY_NAME) {
                    type = NavType.StringType
                    defaultValue = ""
                })
        ) { backStackEntry ->
            DrinksScreen(
                categoryId = requireNotNull(backStackEntry.arguments?.getLong(CATEGORY_ID)),
                categoryName = requireNotNull(backStackEntry.arguments?.getString(CATEGORY_NAME)),
                goToDetailsDrinksScreen = { id ->
                    navController.navigate(route = "$DETAILS_DRINKS_SCREEN/${id}")
                },
                backPressed = {
                    navController.popBackStack()
                })
        }

        composable(
            route = "$DETAILS_DRINKS_SCREEN/{${RoutesArguments.DRINK_ID}}",
            arguments = listOf(
                navArgument(name = RoutesArguments.DRINK_ID) {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )
        ) { backStackEntry ->
            val drinkId =
                requireNotNull(backStackEntry.arguments?.getLong(RoutesArguments.DRINK_ID))
            val viewModel = getViewModel<DrinkDetailsViewModel>(
                parameters = { parametersOf(drinkId) }
            )
            DrinkDetailsScreen(
                viewModel = viewModel,
                backStack = { navController.popBackStack() },
                navigateIngredientDetailsScreen = { id ->
                    navController.navigate(route = "$INGREDIENTS_DETAILS_SCREEN/$id")
                }
            )
        }

        composable(route = "$INGREDIENTS_DETAILS_SCREEN/{${RoutesArguments.INGREDIENT_ID}}",
            arguments = listOf(
                navArgument(name = RoutesArguments.INGREDIENT_ID) {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )) { backStackEntry ->
            val drinkId =
                requireNotNull(backStackEntry.arguments?.getLong(RoutesArguments.INGREDIENT_ID))
            val viewModel = getViewModel<IngredientsDetailsViewModel>(
                parameters = { parametersOf(drinkId) }
            )
            IngredientsDetailsScreen(
                viewModel = viewModel,
                backStack = { navController.popBackStack() },
                goToDetailsDrinkScreen = { id ->
                    navController.navigate(route = "$DETAILS_DRINKS_SCREEN/${id}")
                }
            )
        }
    }
}