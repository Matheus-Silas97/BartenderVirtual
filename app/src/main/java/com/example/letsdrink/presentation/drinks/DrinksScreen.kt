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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.letsdrink.core.commons.TopBar
import com.example.letsdrink.core.components.DrinkCard
import com.example.letsdrink.core.navigation.RoutesNavigation
import com.example.letsdrink.domain.model.Drinks
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinksScreen(goToDetailsDrinksScreen: (id: Long) -> Unit, viewModel: DrinksViewModel = getViewModel()) {
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
                           goToDetailsDrinksScreen(drink.id?: 0L)
                        }
                    }
                }
            }
        })
}

private fun listDrinksMock(): List<Drinks> {
    val link =
        "https://w7.pngwing.com/pngs/388/999/png-transparent-caipirinha-caipiroska-cocktail-cachaca-brazilian-cuisine-cocktail-cocktail-non-alcoholic-beverage-lime-juice-thumbnail.png"
    return listOf(
        Drinks(1, "Caipirinha", image = link),
        Drinks(1, "Caipirinha", image = link),
        Drinks(1, "Caipirinha", image = link),
        Drinks(1, "Caipirinha", image = link),
        Drinks(1, "Caipirinha", image = link)
    )
}

