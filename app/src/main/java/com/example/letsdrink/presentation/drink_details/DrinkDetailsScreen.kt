package com.example.letsdrink.presentation.drink_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letsdrink.common.commons_custom.ImageUrl
import com.example.letsdrink.common.commons_custom.ScaffoldCustom
import com.example.letsdrink.common.commons_custom.TextSubTitle
import com.example.letsdrink.common.commons_custom.TextTitle
import com.example.letsdrink.common.components.IngredientsCard
import org.koin.androidx.compose.getViewModel


@Composable
fun DrinkDetailsScreen(
    drinkId: Long,
    backStack: () -> Unit,
    goToIngredientsDetails: (ingredientId: Long) -> Unit,
    viewModel: DrinkDetailsViewModel = getViewModel()
) {

    val state by viewModel.state.collectAsState()

    val lazyState = rememberLazyListState()

    viewModel.interact(interaction = DrinkDetailsInteraction.GetDrinkDetails(drinkId = drinkId))

    ScaffoldCustom(
        titlePage = state.name,
        onBackPressedEvent = { backStack() },
        showNavigationIcon = true,
        isLoading = state.isLoading
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            ImageUrl(
                url = state.image, modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            TextTitle(text = state.name)

            TextSubTitle(text = "Ingredientes")

            LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                items(items = state.ingredients) { ingredients ->
                    IngredientsCard(ingredients) {id->
                        goToIngredientsDetails(id)
                    }
                }
            }

            TextSubTitle(text = "Modo de preparo")
            LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                items(items = state.ingredients) { step ->
//                    IngredientsCard(step)
                }
            }


        }
    }
}
