package com.example.letsdrink.presentation.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letsdrink.common.commons_custom.ScaffoldCustom
import com.example.letsdrink.common.components.FavoriteCard
import com.example.letsdrink.domain.model.Favorite
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteDrinksScreen(
    goToDetailsDrinkScreen: (id: Long) -> Unit,
    viewModel: FavoriteViewModel = getViewModel()
) {

    val state by viewModel.state.collectAsState()

    viewModel.interact(FavoriteDrinksInteraction.allFavorities)

    ScaffoldCustom(titlePage = "Favoritos") {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(state.favorites) { favorite ->
                FavoriteCard(
                    model = favorite,
                    onClick = { goToDetailsDrinkScreen(favorite.id ?: 0L) },
                    remove = {
                        viewModel.interact(FavoriteDrinksInteraction.RemoveFavorite(drink = it))
                    })
            }
        }
    }
}