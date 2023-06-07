package com.example.letsdrink.presentation.drink_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.letsdrink.common.components.ImageUrl
import com.example.letsdrink.common.components.ScaffoldCustom
import com.example.letsdrink.common.components.TextNormal
import com.example.letsdrink.common.components.TextSubTitle
import com.example.letsdrink.common.components.TextTitle
import com.example.letsdrink.common.components.IngredientsCard
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.CardClickInteraction
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.NavigationClickBackPressed
import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent.GoBack
import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent.NavigateNextScreen


@Composable
fun DrinkDetailsScreen(
    backStack: () -> Unit,
    navigateIngredientDetailsScreen: (String) -> Unit,
    viewModel: DrinkDetailsViewModel
) {
    val state by viewModel.state.collectAsState()

    Content(uiState = state, interaction = viewModel::interact)
    EventConsumer(
        viewModel = viewModel,
        backStack = backStack,
        navigateNextScreen = navigateIngredientDetailsScreen
    )
}

@Composable
private fun EventConsumer(
    viewModel: DrinkDetailsViewModel,
    backStack: () -> Unit,
    navigateNextScreen: (String) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                GoBack -> backStack()
                is NavigateNextScreen -> navigateNextScreen(event.drinkName)
            }
        }
    }
}

@Composable
private fun Content(uiState: DrinkDetailsState, interaction: (DrinkDetailsInteraction) -> Unit) {
    val lazyState = rememberLazyListState()

    ScaffoldCustom(
        titlePage = uiState.name,
        onBackPressedEvent = { interaction(NavigationClickBackPressed) },
        showNavigationIcon = true,
        isLoading = uiState.isLoading
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

            Spacer(modifier = Modifier.height(height = 12.dp))

            TextSubTitle(text = "Ingredientes")
            LazyColumn(state = lazyState) {
                items(items = uiState.ingredients) { ingredients ->
                    IngredientsCard(ingredients) { name ->
                        interaction(CardClickInteraction(name))
                    }
                }
            }

            if (uiState.prepareMode.isNotEmpty()) {
                Spacer(modifier = Modifier.height(height = 12.dp))

                TextSubTitle(text = "Modo de preparo")
                TextNormal(text = uiState.prepareMode)
            }

            if (uiState.garnish.isNotEmpty()) {
                Spacer(modifier = Modifier.height(height = 12.dp))

                TextSubTitle(text = "Guarnição")
                TextNormal(text = uiState.garnish)
            }

            if (uiState.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(height = 12.dp))

                TextSubTitle(text = "Descrição")
                TextNormal(text = uiState.description)
            }
        }
    }
}