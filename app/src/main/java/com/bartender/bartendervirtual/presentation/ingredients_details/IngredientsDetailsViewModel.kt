package com.bartender.bartendervirtual.presentation.ingredients_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bartender.bartendervirtual.common.utils.Event
import com.bartender.bartendervirtual.common.utils.EventImpl
import com.bartender.bartendervirtual.domain.usecase.IngredientsUseCase
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsInteraction.NavigationClickBackPressed
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsInteraction.SelectDrink
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsViewModel.IngredientsDetailsScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientsDetailsViewModel(
    private val ingredientId: Long,
    private val ingredientsUseCase: IngredientsUseCase
) : ViewModel(), Event<IngredientsDetailsScreenEvent> by EventImpl() {

    private val _state = MutableStateFlow(IngredientsDetailsState())
    val state: StateFlow<IngredientsDetailsState> = _state

    init {
        ingredientsDetails(ingredientId = ingredientId)
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

    private fun ingredientsDetails(ingredientId: Long) {
        viewModelScope.launch {
            ingredientsUseCase.ingredientsDetails(ingredientId = ingredientId).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { ingredient ->
                _state.update {
                    it.copy(
                        id = ingredient.id,
                        name = ingredient.name,
                        description = ingredient.description,
                        image = ingredient.image,
                        drinks = ingredient.relatedDrinks,
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