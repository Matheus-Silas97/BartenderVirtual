package com.bartender.bartendervirtual.presentation.drinks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bartender.bartendervirtual.common.utils.Event
import com.bartender.bartendervirtual.common.utils.EventImpl
import com.bartender.bartendervirtual.domain.usecase.DrinksUseCase
import com.bartender.bartendervirtual.presentation.drinks.DrinksInteraction.Categories
import com.bartender.bartendervirtual.presentation.drinks.DrinksInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.drinks.DrinksInteraction.GoBackScreen
import com.bartender.bartendervirtual.presentation.drinks.DrinksInteraction.SelectDrink
import com.bartender.bartendervirtual.presentation.drinks.DrinksViewModel.DrinksEvent
import com.bartender.bartendervirtual.presentation.drinks.DrinksViewModel.DrinksEvent.GoBack
import com.bartender.bartendervirtual.presentation.drinks.DrinksViewModel.DrinksEvent.NavigateDrinkDetailsScreen
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
            is Categories -> getDrinksByCategory(interaction.categoryId)
        }
    }

    private fun getDrinksByCategory(categoryId: Long) {
        viewModelScope.launch {
            drinkUseCase.drinksByCategory(categoryId).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch { error ->
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