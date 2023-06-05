package com.example.letsdrink.presentation.drinks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letsdrink.common.commons_custom.TopBar
import com.example.letsdrink.common.components.DrinkCard
import com.example.letsdrink.domain.model.Drinks
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinksScreen(
    categoryId: Long,
    categoryName: String,
    viewModel: DrinksViewModel = getViewModel(),
    goToDetailsDrinksScreen: (id: Long) -> Unit,
    backPressed: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    val lazyState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBar(
                title = categoryName,
                showNavigationIcon = true,
                onBackPressed = { backPressed.invoke() })
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                    items(items = state.drinks) { drink ->
                        DrinkCard(drink) {
                            goToDetailsDrinksScreen(drink.id ?: 0L)
                        }
                    }
                }
            }
        })
}


