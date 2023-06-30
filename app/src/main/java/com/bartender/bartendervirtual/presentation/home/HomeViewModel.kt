package com.bartender.bartendervirtual.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bartender.bartendervirtual.common.utils.Event
import com.bartender.bartendervirtual.common.utils.EventImpl
import com.bartender.bartendervirtual.domain.usecase.HomeInformationUseCase
import com.bartender.bartendervirtual.presentation.home.HomeInteraction.CloseErrorDialog
import com.bartender.bartendervirtual.presentation.home.HomeInteraction.GoToDetailsDrink
import com.bartender.bartendervirtual.presentation.home.HomeInteraction.NavigateNextScreen
import com.bartender.bartendervirtual.presentation.home.HomeViewModel.HomeScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val homeInformationUseCase: HomeInformationUseCase) : ViewModel(),
    Event<HomeScreenEvent> by EventImpl() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        homeInformation()
    }

    fun interact(interaction: HomeInteraction) {
        when (interaction) {
            CloseErrorDialog -> closeErrorDialog()
            is NavigateNextScreen -> sendEvent(
                event = HomeScreenEvent.NavigateNextScreen(
                    interaction.categoryId, interaction.categoryName
                )
            )

            is GoToDetailsDrink -> sendEvent(event = HomeScreenEvent.GoToDetailsDrink(interaction.drinkId))
        }
    }

    private fun homeInformation() {
        viewModelScope.launch {
            homeInformationUseCase.homeInformation().onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { information ->
                _state.update {
                    it.copy(
                        categories = information.categories,
                        recommendations = information.drinksRecommendations,
                        titleRecommendation = information.title,
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

    sealed interface HomeScreenEvent {
        data class NavigateNextScreen(val categoryId: Long, val categoryName: String) :
            HomeScreenEvent

        data class GoToDetailsDrink(val drinkId: Long) : HomeScreenEvent
    }

}

