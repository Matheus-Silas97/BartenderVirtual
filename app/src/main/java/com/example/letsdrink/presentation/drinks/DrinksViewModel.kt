package com.example.letsdrink.presentation.drinks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.common.utils.Event
import com.example.letsdrink.common.utils.EventImpl
import com.example.letsdrink.domain.usecase.DrinksUseCase
import com.example.letsdrink.presentation.drinks.DrinksInteraction.CloseErrorDialog
import com.example.letsdrink.presentation.drinks.DrinksInteraction.GoBackScreen
import com.example.letsdrink.presentation.drinks.DrinksInteraction.SelectDrink
import com.example.letsdrink.presentation.drinks.DrinksViewModel.DrinksEvent
import com.example.letsdrink.presentation.drinks.DrinksViewModel.DrinksEvent.GoBack
import com.example.letsdrink.presentation.drinks.DrinksViewModel.DrinksEvent.NavigateDrinkDetailsScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrinksViewModel(private val drinkUseCase: DrinksUseCase) : ViewModel(),
    Event<DrinksEvent> by EventImpl() {

    private val _state = MutableStateFlow(DrinksState())
    val state: StateFlow<DrinksState> = _state

    fun interact(interaction: DrinksInteraction) {
        when (interaction) {
            is SelectDrink -> sendEvent(event = NavigateDrinkDetailsScreen(interaction.drinkId))
            is GoBackScreen -> sendEvent(event = GoBack)
            CloseErrorDialog -> closeErrorDialog()
        }
    }

     fun getDrinksByCategory(categoryId: Long) {
        viewModelScope.launch {
            drinkUseCase.drinksByCategory(categoryId).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch { error->
                _state.update { it.copy(isLoading = false, error = error.message) }
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

    private fun closeErrorDialog() {
        _state.update { it.copy(error = null) }
    }

    sealed interface DrinksEvent {
        object GoBack : DrinksEvent
        data class NavigateDrinkDetailsScreen(val drinkId: Long) : DrinksEvent
    }
}