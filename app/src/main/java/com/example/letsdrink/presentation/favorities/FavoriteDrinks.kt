package com.example.letsdrink.presentation.favorities

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
import com.example.letsdrink.domain.model.FavoriteModel

@Composable
fun FavoriteDrinks(navController: NavHostController) {
    ScaffoldCustom(titlePage = "Favoritos", showNavigationIcon = true, onBackPressedEvent = {}) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            items(listOf<FavoriteModel>()) { favorite ->
                FavoriteCard(favorite){
                    navController.navigate(route = RoutesNavigation.DETAILS_DRINKS_SCREEN)
                }
            }
        }
    }
}