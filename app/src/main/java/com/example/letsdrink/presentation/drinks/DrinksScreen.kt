package com.example.letsdrink.presentation.drinks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.letsdrink.common.components.DrinkCard
import com.example.letsdrink.common.components.EmptyListComponent
import com.example.letsdrink.common.components.ErrorDialog
import com.example.letsdrink.common.components.ScaffoldCustom
import com.example.letsdrink.common.utils.extensions.orZero
import com.example.letsdrink.presentation.drinks.DrinksInteraction.CloseErrorDialog
import com.example.letsdrink.presentation.drinks.DrinksInteraction.GoBackScreen
import com.example.letsdrink.presentation.drinks.DrinksInteraction.SelectDrink
import com.example.letsdrink.presentation.drinks.DrinksViewModel.DrinksEvent
import com.example.letsdrink.presentation.drinks.DrinksViewModel.DrinksEvent.NavigateDrinkDetailsScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun DrinksScreen(
    categoryId: Long,
    categoryName: String,
    viewModel: DrinksViewModel = getViewModel(),
    goToDetailsDrinksScreen: (id: Long) -> Unit,
    backPressed: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    Content(categoryName = categoryName, uiState = state, interaction = viewModel::interact)
    EventConsumer(
        viewModel = viewModel,
        backPressed = backPressed,
        goToDetailsDrinksScreen = goToDetailsDrinksScreen
    )

    LaunchedEffect(key1 = Unit){
        viewModel.getDrinksByCategory(categoryId)
    }
}

@Composable
private fun EventConsumer(
    viewModel: DrinksViewModel,
    backPressed: () -> Unit,
    goToDetailsDrinksScreen: (Long) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is DrinksEvent.GoBack -> {
                    backPressed.invoke()
                }

                is NavigateDrinkDetailsScreen -> {
                    goToDetailsDrinksScreen(event.drinkId)
                }
            }
        }
    }
}

@Composable
fun Content(categoryName: String, uiState: DrinksState, interaction: (DrinksInteraction) -> Unit) {
    val lazyState = rememberLazyListState()

    ScaffoldCustom(
        titlePage = categoryName,
        showNavigationIcon = true,
        onBackPressedEvent = { interaction(GoBackScreen) },
        messageLoading = "Carregando bebidas...",
        isLoading = uiState.isLoading
    ) {
        Column(
            modifier = Modifier
                .padding(all = 12.dp)
                .fillMaxSize()
        ) {
            if (!uiState.error.isNullOrEmpty()) {
                ErrorDialog(uiState.error, modifier = Modifier.zIndex(1f)) {
                    interaction(CloseErrorDialog)
                }
            }

            DrinksList(lazyState, uiState, interaction)
        }
    }
}

@Composable
private fun DrinksList(
    lazyState: LazyListState,
    uiState: DrinksState,
    interaction: (DrinksInteraction) -> Unit
) {
    LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
        if (uiState.drinks.isNotEmpty()) {
            items(items = uiState.drinks) { drink ->
                DrinkCard(drink) {
                    interaction(SelectDrink(drinkId = drink.id.orZero()))
                }
            }
        } else {
            item {
                EmptyListComponent("Nenhum drink encontrado")
            }
        }
    }
}


