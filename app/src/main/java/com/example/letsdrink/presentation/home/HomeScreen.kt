package com.example.letsdrink.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letsdrink.common.commons_custom.TopBar
import com.example.letsdrink.common.components.CategoryCard
import com.example.letsdrink.domain.model.Category


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(goToDrinkScreen: (categoryId: Long, categoryName: String) -> Unit) {

    Scaffold(
        topBar = {
            TopBar(
                title = "Let's Drinks",
                showNavigationIcon = false,
                onBackPressed = { })
        }, content = { paddingValues ->
            GridCategories(paddingValues, goToDrinkScreen)
        })


}

@Composable
private fun GridCategories(
    paddingValues: PaddingValues,
    goToDrinkScreen: (categoryId: Long, categoryName: String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(paddingValues),
        columns = Fixed(count = 2),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(items = listCategory()) { category ->
            CategoryCard(category) { categoryId, categoryName ->
                goToDrinkScreen(categoryId, categoryName)
            }
        }
    }
}


private fun listCategory(): List<Category> = listOf(
    Category(1, "cocktails", description = ""),
    Category(1, "inesqueciveis", description = ""),
    Category(1, "new era", description = ""),
    Category(1, "bebida", description = ""),
    Category(1, "tailscock", description = "")
)