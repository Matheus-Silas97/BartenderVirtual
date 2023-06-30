package com.bartender.bartendervirtual.presentation.ingredients_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bartender.bartendervirtual.common.components.DrinkCard
import com.bartender.bartendervirtual.common.components.EmptyListComponent
import com.bartender.bartendervirtual.common.components.ErrorDialog
import com.bartender.bartendervirtual.common.components.ImageUrl
import com.bartender.bartendervirtual.common.components.ScaffoldCustom
import com.bartender.bartendervirtual.common.components.SpacerVertical
import com.bartender.bartendervirtual.common.components.TextSubTitle
import com.bartender.bartendervirtual.common.components.TextTitle
import com.bartender.bartendervirtual.common.theme.Typography
import com.bartender.bartendervirtual.common.utils.extensions.orZero
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsInteraction.NavigationClickBackPressed
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsInteraction.SelectDrink
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsViewModel.IngredientsDetailsScreenEvent

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
    val verticalScrollState = rememberScrollState()

    ScaffoldCustom(
        titlePage = "Detalhes do ingrediente",
        onBackPressedEvent = { interaction(NavigationClickBackPressed) },
        showNavigationIcon = true,
        isLoading = uiState.isLoading,
        messageLoading = "Carregando detalhes do ingrediente..."
    ) {
        if (!uiState.error.isNullOrEmpty()) {
            ErrorDialog(uiState.error, modifier = Modifier.zIndex(1f)) {
                interaction(CloseErrorDialog)
            }
        }

        Column(modifier = Modifier.verticalScroll(state = verticalScrollState)) {
            ImageUrl(
                url = uiState.image, modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillBounds
            )
            SpacerVertical(value = 16)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)

            ) {
                IngredientsInfo(uiState)

                DrinksRelatedList(uiState, interaction)
            }
        }
    }
}

@Composable
private fun IngredientsInfo(uiState: IngredientsDetailsState) {
    TextTitle(text = uiState.name)

    if (uiState.description.isNotEmpty()) {
        SpacerVertical()
        Text(text = uiState.description, style = Typography.bodyMedium)
    }

    SpacerVertical(value = 16)
}


@Composable
private fun DrinksRelatedList(
    uiState: IngredientsDetailsState,
    interaction: (IngredientsDetailsInteraction) -> Unit
) {
    if (uiState.drinks.isNotEmpty()) {
        TextSubTitle(text = "Drinks que utilizam esse ingrediente")
        SpacerVertical()
        uiState.drinks.forEach { drink ->
            DrinkCard(model = drink) {
                interaction(SelectDrink(drinkId = drink.id.orZero()))
            }
        }

    } else {
        EmptyListComponent(msg = "Nenhuma bebida relacionada")
    }
}