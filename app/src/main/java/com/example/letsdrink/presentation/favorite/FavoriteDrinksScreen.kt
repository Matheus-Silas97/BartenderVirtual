package com.example.letsdrink.presentation.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letsdrink.common.commons_custom.ScaffoldCustom
import com.example.letsdrink.common.components.FavoriteCard
import com.example.letsdrink.common.utils.orZero
import com.example.letsdrink.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent
import com.example.letsdrink.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent.*
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteDrinksScreen(
    goToDetailsDrinkScreen: (id: Long) -> Unit,
    viewModel: FavoriteViewModel = getViewModel()
) {

    val state by viewModel.state.collectAsState()
    Content(state = state, interaction = viewModel::interact)
    EventConsumer(viewModel = viewModel, goToDetailsDrinkScreen = goToDetailsDrinkScreen)

}

@Composable
private fun EventConsumer(
    viewModel: FavoriteViewModel,
    goToDetailsDrinkScreen: (id: Long) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is NavigateNextScreen -> goToDetailsDrinkScreen(event.drinkId)
                is RemoveDrinkFromFavorite -> viewModel.interact(
                    interaction = FavoriteDrinksInteraction.RemoveFavorite(
                        event.drink
                    )
                )
            }
        }
    }
}

@Composable
private fun Content(state: FavoritesDrinksState, interaction: (FavoriteDrinksInteraction) -> Unit) {
    ScaffoldCustom(titlePage = "Favoritos") {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(state.favorites) { favorite ->
                FavoriteCard(
                    model = favorite,
                    onClick = { interaction(FavoriteDrinksInteraction.SelectDrink(favorite.id.orZero())) },
                    remove = {
                        interaction(FavoriteDrinksInteraction.RemoveFavorite(drink = favorite))
                    })
            }
        }
    }
}