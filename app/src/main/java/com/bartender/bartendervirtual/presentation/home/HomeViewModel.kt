package com.bartender.bartendervirtual.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bartender.bartendervirtual.common.utils.Event
import com.bartender.bartendervirtual.common.utils.EventImpl
import com.bartender.bartendervirtual.domain.usecase.CategoryUseCase
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

class HomeViewModel(private val categoryUseCase: CategoryUseCase) : ViewModel(),
    Event<HomeScreenEvent> by EventImpl() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        categories()
    }

    fun interact(interaction: HomeInteraction) {
        when (interaction) {
            CloseErrorDialog -> closeErrorDialog()
            is NavigateNextScreen -> sendEvent(
                event = HomeScreenEvent.NavigateNextScreen(
                    interaction.categoryId, interaction.categoryName
                )
            )

            is GoToDetailsDrink -> GoToDetailsDrink(interaction.drinkId)
        }
    }

    private fun categories() {
        viewModelScope.launch {
            categoryUseCase.categories().onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }.catch {
                _state.update { it.copy(isLoading = false, error = it.error) }
            }.collect { categories ->
                _state.update {
                    it.copy(
                        categories = categories,
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

