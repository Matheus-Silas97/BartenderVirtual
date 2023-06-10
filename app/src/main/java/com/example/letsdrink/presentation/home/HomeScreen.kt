package com.example.letsdrink.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.letsdrink.R.string
import com.example.letsdrink.common.components.TopBar
import com.example.letsdrink.common.components.CategoryCard
import com.example.letsdrink.common.components.EmptyListComponent
import com.example.letsdrink.common.components.ErrorDialog
import com.example.letsdrink.common.components.LoadingComponent
import com.example.letsdrink.presentation.home.HomeInteraction.*
import com.example.letsdrink.presentation.home.HomeViewModel.HomeScreenEvent
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    goToDrinkScreen: (categoryId: Long, categoryName: String) -> Unit
) {

    val state by viewModel.state.collectAsState()
    Content(state = state, interaction = viewModel::interact)
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

                HomeScreenEvent.CloseErrorDialog -> viewModel.interact(interaction = CloseErrorDialog)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(state: HomeState, interaction: (HomeInteraction) -> Unit) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = string.app_name)
            )
        }, content = { paddingValues ->
            GridCategories(paddingValues, state, interaction)

            if (state.isLoading) {
                LoadingComponent(
                    modifier = Modifier
                        .zIndex(1f)
                        .padding(paddingValues = paddingValues)
                )
            }


            if (!state.error.isNullOrEmpty()) {
                ErrorDialog(state.error, modifier = Modifier.zIndex(1f)) {
                    interaction(CloseErrorDialog)
                }
            }
        })
}

@Composable
private fun GridCategories(
    paddingValues: PaddingValues,
    uiState: HomeState,
    interaction: (HomeInteraction) -> Unit
) {
    if (uiState.categories.isEmpty()) {
        EmptyListComponent(
            msg = "Sem Categorias",
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        )
    } else {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(paddingValues)
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