package com.example.letsdrink.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.common.utils.Event
import com.example.letsdrink.common.utils.EventImpl
import com.example.letsdrink.domain.model.DrinkFavorite
import com.example.letsdrink.domain.usecase.FavoriteUseCase
import com.example.letsdrink.presentation.favorite.FavoriteDrinksInteraction.RemoveFavorite
import com.example.letsdrink.presentation.favorite.FavoriteDrinksInteraction.SelectDrink
import com.example.letsdrink.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent
import com.example.letsdrink.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent.NavigateNextScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase) : ViewModel(),
    Event<FavoriteScreenEvent> by EventImpl() {

    private val _state = MutableStateFlow(FavoritesDrinksState())
    val state: StateFlow<FavoritesDrinksState> = _state

    //TODO iniciar função

    fun interact(interaction: FavoriteDrinksInteraction) {
        when (interaction) {
            is RemoveFavorite -> deleteFavorite(drink = interaction.drink)
            is SelectDrink -> sendEvent(event = NavigateNextScreen(drinkId = interaction.drinkId))
        }
    }

    private fun allFavorites() {
        viewModelScope.launch {
            favoriteUseCase.allDrinks().onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { favorites ->
                _state.update {
                    it.copy(
                        favorites = favorites,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    private fun deleteFavorite(drink: DrinkFavorite) {
        viewModelScope.launch {
            favoriteUseCase.deleteDrink(drinkFavorite = drink).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { favorites ->
                _state.update {
                    it.copy(
//                        favorites = favorites,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }


    sealed interface FavoriteScreenEvent {
        data class NavigateNextScreen(val drinkId: Long) : FavoriteScreenEvent
        data class RemoveDrinkFromFavorite(val drink: DrinkFavorite) : FavoriteScreenEvent
    }
}