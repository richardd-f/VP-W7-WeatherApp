package com.felix.labw7_weatherapp.ui.screens.homepage

import androidx.lifecycle.ViewModel
import com.felix.labw7_weatherapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    // Private Immutable state
    private val _searchValue: MutableStateFlow<String> = MutableStateFlow("")

    // Dummy WeatherUiState
//    val _weatherUiState: MutableStateFlow<WeatherUiState> = MutableStateFlow(WeatherUiState.Error("HTTP 404 Not Found"))
//    val _weatherUiState: MutableStateFlow<WeatherUiState> = MutableStateFlow(WeatherUiState.Empty)
    val _weatherUiState: MutableStateFlow<WeatherUiState> = MutableStateFlow(WeatherUiState.Success(WeatherData(
        location = "Denpasar, Bali",
        date = "September 30",
        updatedTime = "Updated as of 10:25 AM",

        weatherImgRes = R.drawable.cloud,
        weatherStatus = "Sunny",
        temp = "31°C",
        pandaImgRes = R.drawable.panda_kehujanan,

        humidity = "58%",
        wind = "12 km/h",
        feelsTemp = "33°",
        rainFall = "0.0 mm",
        pressure = "1012 hPa",
        cloudsPercentage = "15%",

        sunriseTime = "05:58 AM",
        sunsetTime = "06:23 PM"
    )
    ))

    // Public Immutable state
    val searchValue: StateFlow<String> = _searchValue.asStateFlow()
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState.asStateFlow()

    // Event
    fun onSearchValueChange(query:String){
        _searchValue.value = query
    }
    fun onClickSearchButton(){
        _weatherUiState.value = WeatherUiState.Loading

    }
}