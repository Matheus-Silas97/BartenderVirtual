package com.example.letsdrink.presentation.ingredients_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.GetDrinkDetails
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.NavigationClickBackPressed
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.SaveDrinkInFavorite
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsInteraction.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientsDetailsViewModel() : ViewModel() {

    private val _state = MutableStateFlow(IngredientsDetailsState())
    val state: StateFlow<IngredientsDetailsState> = _state

    fun interact(interaction: IngredientsDetailsInteraction) {
        when (interaction) {
            is FavoriteDrink -> getFavoriteDrinks()
            is IngredientsDetailsInteraction.NavigationClickBackPressed -> onBackPressed()
            is SelectDrink -> selectDrink(interaction.drinkId)
        }
    }
}

private fun getFavoriteDrinks() {
//    viewModelScope.launch {
//        drinkUseCase.drinkDetail(id = id).onStart {
//            _state.update { it.copy(isLoading = true, error = null) }
//        }.catch {
//            _state.update { it.copy(isLoading = false, error = it.error) }
//        }.collect { drink ->
//            _state.update {
//                it.copy(
//                    id = drink.id,
//                    name = drink.name,
//                    image = drink.image,
//                    description = drink.description,
//                    ingredients = drink.ingredients,
//                    isLoading = false,
//                    error = null
//                )
//            }
//        }
//    }
}

private fun selectDrink(id: Long) {

}


private fun onBackPressed() {

}