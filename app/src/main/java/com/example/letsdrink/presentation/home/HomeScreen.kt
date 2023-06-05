package com.example.letsdrink.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.example.letsdrink.R
import com.example.letsdrink.common.commons_custom.TopBar
import com.example.letsdrink.common.components.CategoryCard
import com.example.letsdrink.common.components.ErrorDialog
import com.example.letsdrink.common.components.LoadingComponent
import com.example.letsdrink.domain.model.Category
import com.example.letsdrink.presentation.home.HomeInteraction.*
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    goToDrinkScreen: (categoryId: Long, categoryName: String) -> Unit
) {

    val state by viewModel.state.collectAsState()

    viewModel.interact(getCateories)

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.app_name)
            )
        }, content = { paddingValues ->
            GridCategories(paddingValues, state, goToDrinkScreen)

            if (state.isLoading) {
                LoadingComponent()
            }

            if (!state.error.isNullOrEmpty()) {
                ErrorDialog(state.error) {
                    viewModel.interact(CloseErrorDialog)
                }
            }
        })
}

@Composable
private fun GridCategories(
    paddingValues: PaddingValues,
    state: HomeState,
    goToDrinkScreen: (categoryId: Long, categoryName: String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(paddingValues),
        columns = Fixed(count = 2),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(items = state.categories) { category ->
            CategoryCard(category) { categoryId, categoryName ->
                goToDrinkScreen(categoryId, categoryName)
            }
        }
    }
}