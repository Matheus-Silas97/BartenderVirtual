package com.example.letsdrink.presentation.drink_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.common.utils.Event
import com.example.letsdrink.common.utils.EventImpl
import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.usecase.DrinksUseCase
import com.example.letsdrink.domain.usecase.FavoriteUseCase
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.CardClickInteraction
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.FavoriteDrink
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.NavigationClickBackPressed
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
    private val drinkUseCase: DrinksUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel(), Event<ScreenEvent> by EventImpl() {

    private val _state = MutableStateFlow(DrinkDetailsState())
    val state: StateFlow<DrinkDetailsState> = _state.asStateFlow()

    init {
        drinkDetails(id = drinkId)
        checkIfDrinkIsFavorite(drinkId = drinkId)
    }

    fun interact(interaction: DrinkDetailsInteraction) {
        when (interaction) {
            is NavigationClickBackPressed -> sendEvent(event = GoBack)
            is FavoriteDrink -> checkFavoriteEvent(interaction.drinkId)
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
                        prepareMode = drink.prepareMode,
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

    private fun checkIfDrinkIsFavorite(drinkId: Long) {
        viewModelScope.launch {
            favoriteUseCase.isFavorite(drinkId = drinkId).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { isFavorite ->
                _state.update {
                    it.copy(
                        isFavorite = isFavorite,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    private fun checkFavoriteEvent(drink: Long) {
        val drinkObject = DrinkDetails(
            id = state.value.id,
            name = state.value.name,
            image = state.value.image,
            description = state.value.description,
            garnish = state.value.garnish,
            prepareMode = state.value.prepareMode
        )
        if (state.value.isFavorite) {
            removeFavoriteDrink(drinkId = drinkObject.id)
        } else {
            addFavoriteDrink(drink = drinkObject)
        }
    }

    private fun addFavoriteDrink(drink: DrinkDetails) {
        viewModelScope.launch {
            favoriteUseCase.insertDrink(drinkFavorite = drink).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { isFavorite ->
                _state.update {
                    it.copy(
//                        isFavorite = isFavorite,
                        isLoading = false,
                        error = null
                    )
                }
                checkIfDrinkIsFavorite(drinkId = drinkId)
            }
        }
    }

    private fun removeFavoriteDrink(drinkId: Long) {
        viewModelScope.launch {
            favoriteUseCase.deleteDrink(drinkId = drinkId).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { isFavorite ->
                _state.update {
                    it.copy(
//                        isFavorite = isFavorite,
                        isLoading = false,
                        error = null
                    )
                }
                checkIfDrinkIsFavorite(drinkId = drinkId)
            }
        }
    }

    sealed interface ScreenEvent {
        object GoBack : ScreenEvent
        data class NavigateNextScreen(val drinkName: Long) : ScreenEvent

    }
}