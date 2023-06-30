package com.bartender.bartendervirtual.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bartender.bartendervirtual.R.string
import com.bartender.bartendervirtual.common.components.CategoryCard
import com.bartender.bartendervirtual.common.components.EmptyListComponent
import com.bartender.bartendervirtual.common.components.ErrorDialog
import com.bartender.bartendervirtual.common.components.ImageUrl
import com.bartender.bartendervirtual.common.components.ScaffoldCustom
import com.bartender.bartendervirtual.common.components.SpacerVertical
import com.bartender.bartendervirtual.common.components.TextSubTitle
import com.bartender.bartendervirtual.domain.model.DrinkHome
import com.bartender.bartendervirtual.presentation.home.HomeInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.home.HomeInteraction.NavigateNextScreen
import com.bartender.bartendervirtual.presentation.home.HomeViewModel.HomeScreenEvent
import com.bartender.bartendervirtual.presentation.home.HomeViewModel.HomeScreenEvent.GoToDetailsDrink
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    goToDrinkScreen: (categoryId: Long, categoryName: String) -> Unit,
    goToDrinkDetails: (id: Long) -> Unit
) {
    val state by viewModel.state.collectAsState()
    Content(uiState = state, interaction = viewModel::interact)
    EventConsumer(
        viewModel = viewModel,
        goToDrinkScreen = goToDrinkScreen,
        goToDrinkDetails = goToDrinkDetails
    )
}

@Composable
private fun EventConsumer(
    viewModel: HomeViewModel,
    goToDrinkScreen: (id: Long, categoryName: String) -> Unit,
    goToDrinkDetails: (id: Long) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is HomeScreenEvent.NavigateNextScreen -> goToDrinkScreen(
                    event.categoryId,
                    event.categoryName
                )

                is GoToDetailsDrink -> goToDrinkDetails(event.drinkId)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Content(uiState: HomeState, interaction: (HomeInteraction) -> Unit) {
    ScaffoldCustom(
        titlePage = stringResource(id = string.app_name),
        isLoading = uiState.isLoading,
        messageLoading = "Carregando categorias"
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            if (uiState.recommendations.isNotEmpty()) {
                SpacerVertical(value = 16)
                SliderImage(uiState = uiState, interaction = interaction)
            }

            SpacerVertical(value = 16)

            CategoriesList(uiState = uiState, interaction = interaction)

            ErrorDialog(uiState, interaction)
        }
    }
}

@Composable
private fun ErrorDialog(
    uiState: HomeState,
    interaction: (HomeInteraction) -> Unit
) {
    if (!uiState.error.isNullOrEmpty()) {
        ErrorDialog(uiState.error, modifier = Modifier.zIndex(1f)) {
            interaction(CloseErrorDialog)
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun SliderImage(uiState: HomeState, interaction: (HomeInteraction) -> Unit) {
    val pagerState = rememberPagerState()
    var currentPage by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            while (true) {
                delay(5000)
                withContext(Dispatchers.Main) {
                    currentPage = (currentPage + 1) % uiState.recommendations.size
                    pagerState.animateScrollToPage(currentPage)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        TextSubTitle(text = uiState.titleRecommendation, modifier = Modifier.padding(start = 16.dp))
        SpacerVertical()
        HorizontalPager(
            count = uiState.recommendations.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            SliderCard(uiState.recommendations[page], select = { id ->
                interaction(HomeInteraction.GoToDetailsDrink(id))
            })
        }
        SpacerVertical()
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = Color.LightGray,
            indicatorHeight = 5.dp,
            indicatorWidth = 5.dp,
            spacing = 22.dp
        )
    }
}

@Composable
fun SliderCard(drink: DrinkHome, select: (id: Long) -> Unit) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Black.copy(alpha = 0.4f))
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { select(drink.id) }
            .height(300.dp), contentAlignment = Alignment.BottomStart
    ) {
        ImageUrl(
            url = drink.image, modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column {
            Card(
                shape = RoundedCornerShape(
                    bottomEnd = 4.dp,
                    bottomStart = 0.dp,
                    topStart = 0.dp,
                    topEnd = 4.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .background(brush = gradient)
                ) {
                    TextSubTitle(
                        text = drink.name,
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 2.dp), textColor = Color.White
                    )
                }
            }
            SpacerVertical()
        }

    }


}


@Composable
private fun CategoriesList(
    uiState: HomeState,
    interaction: (HomeInteraction) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        if (uiState.categories.isEmpty()) {
            EmptyListComponent(
                msg = "Sem Categorias",
                modifier = Modifier
                    .fillMaxSize()
            )
        } else {
            TextSubTitle(text = "Categorias")
            SpacerVertical()
            uiState.categories.forEach { category ->
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