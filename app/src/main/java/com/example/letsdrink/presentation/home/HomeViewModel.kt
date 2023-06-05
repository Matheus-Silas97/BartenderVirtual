package com.example.letsdrink.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsdrink.domain.usecase.CategoryUseCase
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.GetDrinkDetails
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.NavigationClickBackPressed
import com.example.letsdrink.presentation.drink_details.DrinkDetailsInteraction.SaveDrinkInFavorite
import com.example.letsdrink.presentation.home.HomeInteraction.CloseErrorDialog
import com.example.letsdrink.presentation.home.HomeInteraction.getCateories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val categoryUseCase: CategoryUseCase) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun interact(interaction: HomeInteraction) {
        when (interaction) {
            CloseErrorDialog -> closeErrorDialog()
            getCateories -> categories()
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

}

