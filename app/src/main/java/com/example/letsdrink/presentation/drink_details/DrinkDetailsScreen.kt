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
import com.example.letsdrink.common.commons_custom.TextNormal
import com.example.letsdrink.common.commons_custom.TextSubTitle
import com.example.letsdrink.common.commons_custom.TextTitle
import com.example.letsdrink.common.components.IngredientsCard
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.CardClickInteraction
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.NavigationClickBackPressed
import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent.GoBack
import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent.NavigateNextScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun DrinkDetailsScreen(
    backStack: () -> Unit,
    navigateNextScreen: (Long) -> Unit,
    viewModel: DrinkDetailsViewModel
) {

    val state by viewModel.state.collectAsState()
    Content(uiState = state, interaction = viewModel::interact)
    EventConsumer(
        viewModel = viewModel,
        backStack = backStack,
        navigateNextScreen = navigateNextScreen
    )
}

@Composable
private fun EventConsumer(
    viewModel: DrinkDetailsViewModel,
    backStack: () -> Unit,
    navigateNextScreen: (Long) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                GoBack -> backStack()
                is NavigateNextScreen -> navigateNextScreen(event.drinkId)
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
                .padding(8.dp)
        ) {
            ImageUrl(
                url = uiState.image, modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            TextTitle(text = uiState.name)

            TextSubTitle(text = "Ingredientes")
            LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                items(items = uiState.ingredients) { ingredients ->
                    IngredientsCard(ingredients) { id ->
                        interaction(CardClickInteraction(id))
                    }
                }
            }
            TextSubTitle(text = "Modo de preparo")
            TextNormal(text = uiState.prepareMode)
        }
    }
}