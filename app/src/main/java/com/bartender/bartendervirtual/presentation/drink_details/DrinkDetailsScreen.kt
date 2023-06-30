package com.bartender.bartendervirtual.presentation.drink_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bartender.bartendervirtual.R
import com.bartender.bartendervirtual.common.components.EmptyListComponent
import com.bartender.bartendervirtual.common.components.ErrorDialog
import com.bartender.bartendervirtual.common.components.ImageUrl
import com.bartender.bartendervirtual.common.components.IngredientsCard
import com.bartender.bartendervirtual.common.components.ScaffoldCustom
import com.bartender.bartendervirtual.common.components.SpacerVertical
import com.bartender.bartendervirtual.common.components.TextNormal
import com.bartender.bartendervirtual.common.components.TextSubTitle
import com.bartender.bartendervirtual.common.components.TextTitle
import com.bartender.bartendervirtual.presentation.drink_details.DrinkDetailsInteraction.CardClickInteraction
import com.bartender.bartendervirtual.presentation.drink_details.DrinkDetailsInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.drink_details.DrinkDetailsInteraction.NavigationClickBackPressed
import com.bartender.bartendervirtual.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent.GoBack
import com.bartender.bartendervirtual.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent.NavigateNextScreen


@Composable
fun DrinkDetailsScreen(
    backStack: () -> Unit,
    navigateIngredientDetailsScreen: (Long) -> Unit,
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
    navigateNextScreen: (Long) -> Unit,
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
    val verticalScrollState = rememberScrollState()

    ScaffoldCustom(
        titlePage = "Detalhes do drink",
        onBackPressedEvent = { interaction(NavigationClickBackPressed) },
        showNavigationIcon = true,
        isLoading = uiState.isLoading,
        messageLoading = "Carregando detalhes..."
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
                Row {
                    TextTitle(text = uiState.name, modifier = Modifier.weight(1F))

                    var favoriteIcon = painterResource(id = R.drawable.ic_favorite_unselect)
                    if (uiState.isFavorite) {
                        favoriteIcon = painterResource(id = R.drawable.ic_favorite_select)
                    }
                    Image(
                        painter = favoriteIcon,
                        contentDescription = "favorite icon",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .clickable {
                                interaction(DrinkDetailsInteraction.FavoriteDrink(uiState.id))
                            })
                }

                if (uiState.description.isNotEmpty()) {
                    SpacerVertical()
                    TextNormal(text = uiState.description, color = Color.Gray)
                }

                SpacerVertical(value = 16)

                if (uiState.ingredients.isNotEmpty()) {
                    TextSubTitle(text = "Ingredientes")
                    SpacerVertical()
                    uiState.ingredients.forEach { ingredient ->
                        IngredientsCard(ingredient) { id ->
                            interaction(CardClickInteraction(id))
                        }
                    }
                } else {
                    EmptyListComponent(msg = "Sem ingredientes")
                }

                if (uiState.prepareMode.isNotEmpty()) {
                    SpacerVertical(value = 16)
                    TextSubTitle(text = "Modo de preparo")
                    SpacerVertical()
                    TextNormal(text = uiState.prepareMode)
                    SpacerVertical(value = 16)
                }

                if (uiState.garnish.isNotEmpty()) {
                    TextSubTitle(text = "Guarnição")
                    SpacerVertical()
                    TextNormal(text = uiState.garnish)
                    SpacerVertical(value = 16)
                }
            }
        }
    }
}