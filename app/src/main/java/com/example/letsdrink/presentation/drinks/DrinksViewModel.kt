package com.example.letsdrink.presentation.drinks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.domain.usecase.DrinksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrinksViewModel(private val drinkUseCase: DrinksUseCase) : ViewModel() {

    private val _state = MutableStateFlow(DrinksState())
    val state: StateFlow<DrinksState> = _state

    fun interact(interaction: DrinksInteraction) {
        when (interaction) {
            is DrinksInteraction.SelectDrink -> {}
        }
    }

    private fun getDrinksByCategory(categoryId: Long) {
        viewModelScope.launch {
            drinkUseCase.drinksByCategory(categoryId).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { drink ->
                _state.update {
                    it.copy(
                        drinks = drink,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }


    private fun drinkDetails(id: Long) {
//        viewModelScope.launch {
//            drinkUseCase.allDrinks().onStart {
//                _state.update { it.copy(isLoading = true, error = null) }
//            }.catch {
//                _state.update { it.copy(isLoading = false, error = it.error) }
//            }.collect { drink ->
//                _state.update {
////                    it.copy(drinks = drinks
////                        id = drink.id,
////                        name = drink.name,
////                        image = drink.image,
////                        description = drink.description,
////                        ingredients = drink.ingredients,
////                        isLoading = false,
////                        error = null
////                    )
//                }
//            }
//        }
    }
}