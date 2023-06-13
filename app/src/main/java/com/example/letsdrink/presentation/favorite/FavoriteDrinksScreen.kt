package com.example.letsdrink.presentation.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.letsdrink.R.string
import com.example.letsdrink.common.components.EmptyListComponent
import com.example.letsdrink.common.components.ErrorDialog
import com.example.letsdrink.common.components.FavoriteCard
import com.example.letsdrink.common.components.LoadingComponent
import com.example.letsdrink.common.components.ScaffoldCustom
import com.example.letsdrink.common.components.TopBar
import com.example.letsdrink.common.utils.extensions.orZero
import com.example.letsdrink.presentation.favorite.FavoriteDrinksInteraction.CloseErrorDialog
import com.example.letsdrink.presentation.favorite.FavoriteDrinksInteraction.RemoveFavorite
import com.example.letsdrink.presentation.favorite.FavoriteDrinksInteraction.SelectDrink
import com.example.letsdrink.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent.*
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteDrinksScreen(
    goToDetailsDrinkScreen: (id: Long) -> Unit,
    viewModel: FavoriteViewModel = getViewModel()
) {

    val state by viewModel.state.collectAsState()
    Content(uiState = state, interaction = viewModel::interact)
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
                    interaction = RemoveFavorite(event.drinkId)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    uiState: FavoritesDrinksState,
    interaction: (FavoriteDrinksInteraction) -> Unit
) {
    ScaffoldCustom(
        titlePage = stringResource(id = string.favorites),
        isLoading = uiState.isLoading,
        messageLoading = "carregando drinks favoritos..."
    ) {
        if (!uiState.error.isNullOrEmpty()) {
            ErrorDialog(uiState.error, modifier = Modifier.zIndex(1f)) {
                interaction(CloseErrorDialog)
            }
        }

        FavoritesList(uiState, interaction)
    }
}

@Composable
private fun FavoritesList(
    uiState: FavoritesDrinksState,
    interaction: (FavoriteDrinksInteraction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
        if (uiState.favorites.isNotEmpty()) {
            items(uiState.favorites) { favorite ->
                FavoriteCard(
                    model = favorite,
                    onClick = { interaction(SelectDrink(favorite.id.orZero())) },
                    remove = {
                        interaction(RemoveFavorite(drinkId = favorite.id))
                    })
            }
        } else {
            item {
                EmptyListComponent(msg = "Você não possui bebidas favoritadas")
            }
        }
    }
}