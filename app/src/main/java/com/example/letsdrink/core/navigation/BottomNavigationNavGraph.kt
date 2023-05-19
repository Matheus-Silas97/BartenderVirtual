package com.example.letsdrink.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.letsdrink.presentation.drink_details.DrinkDetailsScreen
import com.example.letsdrink.presentation.drinks.DrinksScreen

@Composable
fun BottomNavigationNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.DrinksScreen.route
    ) {
        composable(BottomNavigationScreens.DrinksScreen.route) { DrinksScreen() }
        composable(BottomNavigationScreens.Screen2.route) { DrinkDetailsScreen() }
        composable(BottomNavigationScreens.Screen3.route) { DrinksScreen() }
    }
}