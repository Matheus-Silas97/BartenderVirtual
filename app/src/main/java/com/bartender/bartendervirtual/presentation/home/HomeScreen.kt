package com.bartender.bartendervirtual.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bartender.bartendervirtual.R.string
import com.bartender.bartendervirtual.common.components.CategoryCard
import com.bartender.bartendervirtual.common.components.EmptyListComponent
import com.bartender.bartendervirtual.common.components.ErrorDialog
import com.bartender.bartendervirtual.common.components.ScaffoldCustom
import com.bartender.bartendervirtual.presentation.home.HomeInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.home.HomeInteraction.NavigateNextScreen
import com.bartender.bartendervirtual.presentation.home.HomeViewModel.HomeScreenEvent
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    goToDrinkScreen: (categoryId: Long, categoryName: String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    Content(uiState = state, interaction = viewModel::interact)
    EventConsumer(viewModel = viewModel, goToDrinkScreen = goToDrinkScreen)
}

@Composable
private fun EventConsumer(
    viewModel: HomeViewModel,
    goToDrinkScreen: (id: Long, categoryName: String) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is HomeScreenEvent.NavigateNextScreen -> goToDrinkScreen(
                    event.categoryId,
                    event.categoryName
                )
            }
        }
    }
}

@Composable
fun Content(uiState: HomeState, interaction: (HomeInteraction) -> Unit) {

    ScaffoldCustom(
        titlePage = stringResource(id = string.app_name),
        isLoading = uiState.isLoading,
        messageLoading = "Carregando categorias"
    ) {
        GridCategories(uiState, interaction)

        if (!uiState.error.isNullOrEmpty()) {
            ErrorDialog(uiState.error, modifier = Modifier.zIndex(1f)) {
                interaction(CloseErrorDialog)
            }
        }
    }
}

@Composable
private fun GridCategories(
    uiState: HomeState,
    interaction: (HomeInteraction) -> Unit
) {
    if (uiState.categories.isEmpty()) {
        EmptyListComponent(
            msg = "Sem Categorias",
            modifier = Modifier
                .fillMaxSize()
        )
    } else {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = Fixed(count = 2),
            contentPadding = PaddingValues(all = 16.dp),
        ) {
            items(items = uiState.categories) { category ->
                CategoryCard(category) { categoryId, categoryName ->
                    interaction(
                        NavigateNextScreen(
                            categoryId = categoryId,
                            categoryName = categoryName
                        )
                    )
                }
            }
        }
    }
}