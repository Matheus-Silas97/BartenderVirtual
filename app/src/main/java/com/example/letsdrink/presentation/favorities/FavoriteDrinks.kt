package com.example.letsdrink.presentation.favorities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letsdrink.core.commons.ScaffoldCustom
import com.example.letsdrink.core.components.FavoritiesCard
import com.example.letsdrink.domain.model.FavoriteModel

@Composable
fun FavoriteDrinks() {
    ScaffoldCustom(titlePage = "Favoritos", showNavigationIcon = true, onBackPressedEvent = {}) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            items(listOf<FavoriteModel>()) { favorite ->
                FavoritiesCard(favorite)
            }
        }
    }
}