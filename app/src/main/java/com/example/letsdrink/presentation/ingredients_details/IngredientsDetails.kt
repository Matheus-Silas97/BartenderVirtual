package com.example.letsdrink.presentation.ingredients_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.letsdrink.core.commons.ImageUrl
import com.example.letsdrink.core.commons.ScaffoldCustom
import com.example.letsdrink.core.components.DrinkCard
import com.example.letsdrink.core.components.IngredientsCard
import com.example.letsdrink.core.navigation.RoutesNavigation
import com.example.letsdrink.core.theme.Typography
import com.example.letsdrink.domain.model.IngredientsModel

@Composable
fun IngredientsDetailsScreen(ingredientId: Long, navController: NavHostController) {
    val lazyState = rememberLazyListState()
    val ingredientInfo = IngredientsModel()

    ScaffoldCustom(
        titlePage = ingredientInfo.name,
        onBackPressedEvent = {},
        showNavigationIcon = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ImageUrl(
                url = "", modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Text(text = ingredientInfo.name, style = Typography.titleLarge)

            Text(text = "Descrição", style = Typography.titleMedium)

            Text(text = ingredientInfo.description, style = Typography.bodyMedium)

            Text(text = "Drinks que utilizam esses ingredientes", style = Typography.titleMedium)

            LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                items(items = ingredientInfo.relatedDrinks) { drinks ->
                    DrinkCard(drinks) {
                        navController.navigate(route = RoutesNavigation.DETAILS_DRINKS_SCREEN)
                    }
                }
            }

        }
    }
}