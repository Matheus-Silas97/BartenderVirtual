package com.example.letsdrink.presentation.drink_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.common.utils.Event
import com.example.letsdrink.common.utils.EventImpl
import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.usecase.DrinksUseCase
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.NavigationClickBackPressed
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.SaveDrinkInFavorite
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.CardClickInteraction
import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent
import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent.GoBack
import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel.ScreenEvent.NavigateNextScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrinkDetailsViewModel(
    private val drinkId: Long,
    private val drinkUseCase: DrinksUseCase
) : ViewModel(), Event<ScreenEvent> by EventImpl() {

    private val _state = MutableStateFlow(DrinkDetailsState())
    val state: StateFlow<DrinkDetailsState> = _state.asStateFlow()

    init {
        drinkDetails(id = drinkId)
    }

    fun interact(interaction: DrinkDetailsInteraction) {
        when (interaction) {
            is NavigationClickBackPressed -> sendEvent(event = GoBack)
            is SaveDrinkInFavorite -> favoriteDrink(interaction.drink)
            is CardClickInteraction -> sendEvent(event = NavigateNextScreen(interaction.drinkId))
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
                        prepareMode = drink.modePrepare,
                        description = drink.description,
                        garnish = drink.garnish,
                        ingredients = drink.ingredients,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    private fun favoriteDrink(postalCode: DrinkDetails) = viewModelScope.launch {
    }

    sealed interface ScreenEvent {
        object GoBack : ScreenEvent
        data class NavigateNextScreen(val drinkName: Long) : ScreenEvent
    }
}