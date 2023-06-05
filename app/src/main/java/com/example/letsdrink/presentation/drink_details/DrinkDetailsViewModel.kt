package com.example.letsdrink.presentation.drink_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.usecase.DrinksUseCase
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.NavigationClickBackPressed
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.SaveDrinkInFavorite
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.GetDrinkDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrinkDetailsViewModel(private val drinkUseCase: DrinksUseCase) : ViewModel() {
    private val _state = MutableStateFlow(DrinkDetailsState())
    val state: StateFlow<DrinkDetailsState> = _state


    fun interact(interaction: DrinkDetailsInteraction) {
        when (interaction) {
            is NavigationClickBackPressed -> onBackPressed()
            is SaveDrinkInFavorite -> favoriteDrink(interaction.drink)
            is GetDrinkDetails -> drinkDetails(interaction.drinkId)
        }
    }

    private fun drinkDetails(id: Long) {
        viewModelScope.launch {
            drinkUseCase.drinkDetail(id = id).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { drink ->
                _state.update {
                    it.copy(
                        id = drink.id,
                        name = drink.name,
                        image = drink.image,
                        description = drink.description,
                        ingredients = drink.ingredients,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }


    private fun favoriteDrink(postalCode: DrinkDetails) {
        viewModelScope.launch {

        }
    }

    fun onBackPressed() {

    }

}