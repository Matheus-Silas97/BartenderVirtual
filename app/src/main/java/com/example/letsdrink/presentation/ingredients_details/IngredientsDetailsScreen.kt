package com.example.letsdrink.presentation.ingredients_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letsdrink.common.components.ImageUrl
import com.example.letsdrink.common.components.ScaffoldCustom
import com.example.letsdrink.common.components.TextSubTitle
import com.example.letsdrink.common.components.TextTitle
import com.example.letsdrink.common.components.DrinkCard
import com.example.letsdrink.common.theme.Typography
import com.example.letsdrink.common.utils.extensions.orZero
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsInteraction.NavigationClickBackPressed
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsInteraction.SelectDrink
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsViewModel.IngredientsDetailsScreenEvent

@Composable
fun IngredientsDetailsScreen(
    backStack: () -> Unit,
    goToDetailsDrinkScreen: (id: Long) -> Unit,
    viewModel: IngredientsDetailsViewModel
) {
    val state by viewModel.state.collectAsState()

    Content(uiState = state, interaction = viewModel::interact)
    EventConsumer(viewModel = viewModel, backStack, goToDetailsDrinkScreen)

}

@Composable
private fun EventConsumer(
    viewModel: IngredientsDetailsViewModel,
    backStack: () -> Unit,
    goToDetailsDrinkScreen: (id: Long) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                IngredientsDetailsScreenEvent.GoBack -> backStack()
                is IngredientsDetailsScreenEvent.NavigateNextScreen -> goToDetailsDrinkScreen(event.drinkId)
            }
        }
    }
}

@Composable
fun Content(
    uiState: IngredientsDetailsState,
    interaction: (IngredientsDetailsInteraction) -> Unit
) {
    val lazyState = rememberLazyListState()

    ScaffoldCustom(
        titlePage = uiState.name,
        onBackPressedEvent = { interaction(NavigationClickBackPressed) },
        showNavigationIcon = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 12.dp)
        ) {
            ImageUrl(
                url = uiState.image, modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            TextTitle(text = uiState.name)

            if (uiState.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(height = 12.dp))

                TextSubTitle(text = "Descrição")
                Text(text = uiState.description, style = Typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(height = 12.dp))

            TextSubTitle(text = "Drinks que utilizam esses ingredientes")
            LazyColumn(state = lazyState) {
                items(items = uiState.drinks) { drink ->
                    DrinkCard(model = drink) {
                        interaction(SelectDrink(drinkId = drink.id.orZero()))

                    }
                }
            }

        }
    }
}