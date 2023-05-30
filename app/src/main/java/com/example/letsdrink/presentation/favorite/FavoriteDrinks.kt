package com.example.letsdrink.presentation.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.letsdrink.core.commons.ScaffoldCustom
import com.example.letsdrink.core.components.FavoriteCard
import com.example.letsdrink.core.navigation.RoutesNavigation
import com.example.letsdrink.domain.model.Favorite
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteDrinks(navController: NavHostController, viewModel: FavoriteViewModel = getViewModel()) {
    ScaffoldCustom(titlePage = "Favoritos") {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(listOf<Favorite>()) { favorite ->
                FavoriteCard(
                    model = favorite,
                    onClick = { navController.navigate(route = "${RoutesNavigation.DETAILS_DRINKS_SCREEN}/${favorite.id}") },
                    remove = {
                        //TODO
                    })
            }
        }
    }
}