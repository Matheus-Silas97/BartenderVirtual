package com.bartender.bartendervirtual.presentation.drinks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.zIndex
import com.bartender.bartendervirtual.common.components.DrinkCard
import com.bartender.bartendervirtual.common.components.EmptyListComponent
import com.bartender.bartendervirtual.common.components.ErrorDialog
import com.bartender.bartendervirtual.common.components.ScaffoldCustom
import com.bartender.bartendervirtual.common.utils.extensions.orZero
import com.bartender.bartendervirtual.presentation.drinks.DrinksInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.drinks.DrinksInteraction.GoBackScreen
import com.bartender.bartendervirtual.presentation.drinks.DrinksInteraction.SelectDrink
import com.bartender.bartendervirtual.presentation.drinks.DrinksViewModel.DrinksEvent
import com.bartender.bartendervirtual.presentation.drinks.DrinksViewModel.DrinksEvent.NavigateDrinkDetailsScreen
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

    LaunchedEffect(key1 = Unit) {
        viewModel.interact(DrinksInteraction.Categories(categoryId = categoryId))
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
    ScaffoldCustom(
        titlePage = categoryName,
        showNavigationIcon = true,
        onBackPressedEvent = { interaction(GoBackScreen) },
        messageLoading = "Carregando bebidas...",
        isLoading = uiState.isLoading
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .fillMaxSize()
        ) {
            if (!uiState.error.isNullOrEmpty()) {
                ErrorDialog(uiState.error, modifier = Modifier.zIndex(1f)) {
                    interaction(CloseErrorDialog)
                }
            }

            DrinksList(uiState, interaction)
        }
    }
}

@Composable
private fun DrinksList(
    uiState: DrinksState,
    interaction: (DrinksInteraction) -> Unit
) {
    val lazyState = rememberLazyListState()

    LazyColumn(state = lazyState, contentPadding = PaddingValues(vertical = 3.dp)) {
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


