package com.example.letsdrink.presentation.favorite

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.letsdrink.R.string
import com.example.letsdrink.common.components.EmptyListComponent
import com.example.letsdrink.common.components.ErrorDialog
import com.example.letsdrink.common.components.FavoriteCard
import com.example.letsdrink.common.components.ScaffoldCustom
import com.example.letsdrink.common.utils.NetworkingHelper
import com.example.letsdrink.common.utils.extensions.orZero
import com.example.letsdrink.presentation.favorite.FavoriteDrinksInteraction.*
import com.example.letsdrink.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent.NavigateNextScreen
import com.example.letsdrink.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent.RemoveDrinkFromFavorite
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteDrinksScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    goToDetailsDrinkScreen: (id: Long) -> Unit,
    viewModel: FavoriteViewModel = getViewModel()
) {

    val state by viewModel.state.collectAsState()
    Content(uiState = state, interaction = viewModel::interact)
    EventConsumer(viewModel = viewModel, goToDetailsDrinkScreen = goToDetailsDrinkScreen)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.interact(AllFavoritesDrinks)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

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
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
        if (uiState.favorites.isNotEmpty()) {
            items(uiState.favorites) { favorite ->
                FavoriteCard(
                    model = favorite,
                    onClick = {
                        if (NetworkingHelper().isInternetConnected(context)) {
                            interaction(SelectDrink(favorite.id.orZero()))
                        } else {
                            Toast.makeText(
                                context,
                                "Sem conexão com a internet",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
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