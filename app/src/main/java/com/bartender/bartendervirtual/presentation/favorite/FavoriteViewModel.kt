package com.bartender.bartendervirtual.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bartender.bartendervirtual.common.utils.Event
import com.bartender.bartendervirtual.common.utils.EventImpl
import com.bartender.bartendervirtual.domain.usecase.FavoriteUseCase
import com.bartender.bartendervirtual.presentation.favorite.FavoriteDrinksInteraction.AllFavoritesDrinks
import com.bartender.bartendervirtual.presentation.favorite.FavoriteDrinksInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.favorite.FavoriteDrinksInteraction.RemoveFavorite
import com.bartender.bartendervirtual.presentation.favorite.FavoriteDrinksInteraction.SelectDrink
import com.bartender.bartendervirtual.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent
import com.bartender.bartendervirtual.presentation.favorite.FavoriteViewModel.FavoriteScreenEvent.NavigateNextScreen
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

    init {
        allFavorites()
    }

    fun interact(interaction: FavoriteDrinksInteraction) {
        when (interaction) {
            is RemoveFavorite -> removeFavoriteDrink(drinkId = interaction.drinkId)
            is SelectDrink -> sendEvent(event = NavigateNextScreen(drinkId = interaction.drinkId))
            CloseErrorDialog -> closeErrorDialog()
            is AllFavoritesDrinks -> allFavorites()
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

    private fun removeFavoriteDrink(drinkId: Long) {
        viewModelScope.launch {
            favoriteUseCase.deleteDrink(drinkId = drinkId).onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { isFavorite ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = null
                    )
                }
                allFavorites()
            }
        }
    }

    private fun closeErrorDialog() {
        _state.update { it.copy(error = null) }
    }


    sealed interface FavoriteScreenEvent {
        data class NavigateNextScreen(val drinkId: Long) : FavoriteScreenEvent
        data class RemoveDrinkFromFavorite(val drinkId: Long) : FavoriteScreenEvent
    }
}