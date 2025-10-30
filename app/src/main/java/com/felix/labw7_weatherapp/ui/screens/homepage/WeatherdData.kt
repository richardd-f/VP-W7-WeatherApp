package com.felix.labw7_weatherapp.ui.screens.homepage

data class WeatherData(
    val location: String,
    val date: String,
    val updatedTime: String,

    val weatherIconUrl: String,
    val weatherStatus: String,
    val temp: String,
    val pandaImgRes: Int,

    val humidity: String,
    val wind: String,
    val feelsTemp: String,
    val rainFall: String,
    val pressure: String,
    val cloudsPercentage: String,

    val sunriseTime: String,
    val sunsetTime: String
)

// This sealed interface will represent screen's state
sealed interface WeatherUiState {
    object Loading : WeatherUiState
    object Empty : WeatherUiState
    data class Success(val data: WeatherData) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}