package com.example.letsdrink.presentation.drink_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letsdrink.core.commons.ImageUrl
import com.example.letsdrink.core.commons.ScaffoldCustom
import com.example.letsdrink.core.commons.TopBar
import com.example.letsdrink.core.components.IngredientsCard
import com.example.letsdrink.core.theme.Typography
import com.example.letsdrink.domain.model.DrinkDetailsModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailsScreen() {

    val drink = DrinkDetailsModel()
    val lazyState = rememberLazyListState()

    ScaffoldCustom(titlePage = drink.name, onBackPressedEvent = {}) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ImageUrl(
                url = "", modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Text(text = drink.name, style = Typography.titleLarge)

            Text(text = "Ingredientes", style = Typography.titleMedium)
            LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                items(items = drink.ingredients) { ingredients ->
                    IngredientsCard(ingredients)
                }
            }

            Text(text = "Modo de preparo", style = Typography.titleMedium)
            LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                items(items = drink.modePrepare) { step ->
//                    IngredientsCard(step)
                }
            }


        }
    }
}
