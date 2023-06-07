package com.example.letsdrink.presentation.ingredients_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.common.utils.Event
import com.example.letsdrink.common.utils.EventImpl
import com.example.letsdrink.domain.usecase.IngredientsUseCase
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsInteraction.*
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsViewModel.IngredientsDetailsScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientsDetailsViewModel(
    private val ingredientName: String,
    private val ingredientsUseCase: IngredientsUseCase
) : ViewModel(), Event<IngredientsDetailsScreenEvent> by EventImpl() {

    private val _state = MutableStateFlow(IngredientsDetailsState())
    val state: StateFlow<IngredientsDetailsState> = _state

    init {
        ingredientsDetails(ingredientName = ingredientName)
    }

    fun interact(interaction: IngredientsDetailsInteraction) {
        when (interaction) {
            is NavigationClickBackPressed -> sendEvent(event = IngredientsDetailsScreenEvent.GoBack)
            is CloseErrorDialog -> closeErrorDialog()
            is SelectDrink -> sendEvent(
                event = IngredientsDetailsScreenEvent.NavigateNextScreen(
                    interaction.drinkId
                )
            )
        }
    }

    private fun ingredientsDetails(ingredientName: String) {
        viewModelScope.launch {
            ingredientsUseCase.ingredientsDetails(ingredientName = ingredientName).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { ingredients ->
                _state.update {
                    it.copy(
                        drinks = ingredients,
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

    sealed interface IngredientsDetailsScreenEvent {
        data class NavigateNextScreen(val drinkId: Long) : IngredientsDetailsScreenEvent

        object GoBack : IngredientsDetailsScreenEvent

    }
}