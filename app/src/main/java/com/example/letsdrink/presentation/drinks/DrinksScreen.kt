package com.example.letsdrink.presentation.drinks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.letsdrink.R
import com.example.letsdrink.core.commons.TopBar
import com.example.letsdrink.core.components.DrinkCard
import com.example.letsdrink.core.navigation.RoutesNavigation
import com.example.letsdrink.domain.model.DrinksModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinksScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Let's Drinks",
                showNavigationIcon = false,
                onBackPressed = { })
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                val lazyState = rememberLazyListState()
                LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                    items(items = listDrinksMock()) { drink ->
                        DrinkCard(drink) {
                            navController.navigate(route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/${drink.id}")
                        }
                    }
                }
            }
        })
}

private fun listDrinksMock(): List<DrinksModel> {
    val link =
        "https://w7.pngwing.com/pngs/388/999/png-transparent-caipirinha-caipiroska-cocktail-cachaca-brazilian-cuisine-cocktail-cocktail-non-alcoholic-beverage-lime-juice-thumbnail.png"
    return listOf(
        DrinksModel(1, "Caipirinha", image = link),
        DrinksModel(1, "Caipirinha", image = link),
        DrinksModel(1, "Caipirinha", image = link),
        DrinksModel(1, "Caipirinha", image = link),
        DrinksModel(1, "Caipirinha", image = link)
    )
}

