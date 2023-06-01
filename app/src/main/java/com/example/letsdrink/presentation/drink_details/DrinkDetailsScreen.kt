package com.example.letsdrink.presentation.drink_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.letsdrink.core.commons.ImageUrl
import com.example.letsdrink.core.commons.ScaffoldCustom
import com.example.letsdrink.core.commons.TextSubTitle
import com.example.letsdrink.core.commons.TextTitle
import com.example.letsdrink.core.components.IngredientsCard
import com.example.letsdrink.domain.model.DrinkDetails
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailsScreen(
    drinkId: Long,
    backStack: () -> Unit,
    viewModel: DrinkDetailsViewModel = getViewModel()
) {

    val drink = DrinkDetails()
    val lazyState = rememberLazyListState()

    ScaffoldCustom(
        titlePage = drink.name,
        onBackPressedEvent = { backStack() },
        showNavigationIcon = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            ImageUrl(
                url = "", modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            TextTitle(text = drink.name)

            TextSubTitle(text = "Ingredientes")

            LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                items(items = drink.ingredients) { ingredients ->
                    IngredientsCard(ingredients) {
                        //Todo
                    }
                }
            }

            TextSubTitle(text = "Modo de preparo")
            LazyColumn(state = lazyState, modifier = Modifier.padding(all = 8.dp)) {
                items(items = drink.modePrepare) { step ->
//                    IngredientsCard(step)
                }
            }


        }
    }
}
