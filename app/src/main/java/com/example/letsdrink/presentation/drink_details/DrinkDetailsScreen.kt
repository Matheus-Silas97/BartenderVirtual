package com.example.letsdrink.presentation.drink_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.letsdrink.R
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
        titlePage = uiState.name,
        onBackPressedEvent = { interaction(NavigationClickBackPressed) },
        showNavigationIcon = true,
        isLoading = uiState.isLoading
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 12.dp)
                .verticalScroll(state = verticalScrollState)
        ) {
            ImageUrl(
                url = uiState.image, modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

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



            Spacer(modifier = Modifier.height(height = 12.dp))

            TextSubTitle(text = "Ingredientes")

            for (ingredient in uiState.ingredients) {
                IngredientsCard(ingredient) { id ->
                    interaction(CardClickInteraction(id))
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