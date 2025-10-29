package com.felix.labw7_weatherapp.ui.screens.homepage

import com.felix.labw7_weatherapp.R

data class WeatherData(
    val location: String = "Loading...",
    val date: String = "...",
    val updatedTime: String = "...",

    val weatherImgRes: Int = R.drawable.cloud,
    val weatherStatus: String = "...",
    val temp: String = "--°C",
    val pandaImgRes: Int = R.drawable.panda_kehujanan,

    val humidity: String = "--%",
    val wind: String = "--km/h",
    val feelsTemp: String = "--°",
    val rainFall: String = "-- mm",
    val pressure: String = "-- hPa",
    val cloudsPercentage: String = "--%",

    val sunriseTime: String = "--:-- AM",
    val sunsetTime: String = "--:-- PM"
)

// This sealed interface will represent screen's state
sealed interface WeatherUiState {
    object Loading : WeatherUiState
    object Empty : WeatherUiState
    data class Success(val data: WeatherData) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}