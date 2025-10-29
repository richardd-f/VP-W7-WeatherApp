package com.felix.labw7_weatherapp.ui.screens.homepage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    // Private Immutable state
    private val _searchValue: MutableStateFlow<String> = MutableStateFlow("")
    val _weatherUiState: MutableStateFlow<WeatherUiState> = MutableStateFlow(WeatherUiState.Loading)

    // Public Imutable state
    val searchValue: StateFlow<String> = _searchValue.asStateFlow()
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState.asStateFlow()

    // Event
    fun onSearchValueChange(query:String){
        _searchValue.value = query
    }
}