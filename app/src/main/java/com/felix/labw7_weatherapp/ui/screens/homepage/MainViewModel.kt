package com.felix.labw7_weatherapp.ui.screens.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.labw7_weatherapp.R
import com.felix.labw7_weatherapp.data.model.WeatherApiResponse
import com.felix.labw7_weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    // Private Immutable state
    private val _searchValue: MutableStateFlow<String> = MutableStateFlow("")
    private val _weatherUiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Empty)

    // Public Immutable state
    val searchValue: StateFlow<String> = _searchValue.asStateFlow()
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState.asStateFlow()

    // Event
    fun onSearchValueChange(query: String) {
        _searchValue.value = query
    }

    // 4. Implement the fetch logic
    fun onClickSearchButton() {
        // Don't search if the query is blank
        if (_searchValue.value.isBlank()) {
            return
        }

        viewModelScope.launch {
            // Set Loading state
            _weatherUiState.value = WeatherUiState.Loading

            // Call the repository and collect the Flow
            repository.getWeather(_searchValue.value)
                .collect { result ->
                    result.fold(
                        onSuccess = { response ->
                            // On success, map the API data to our UI data
                            _weatherUiState.value = WeatherUiState.Success(
                                mapResponseToWeatherData(response)
                            )
                        },
                        onFailure = { error ->
                            // On failure, show an error message
                            _weatherUiState.value = WeatherUiState.Error(
                                error.message ?: "An unknown error occurred"
                            )
                        }
                    )
                }
        }
    }

    // 5. Add a helper function to map API data to UI data
    private fun mapResponseToWeatherData(response: WeatherApiResponse): WeatherData {
        val pandaImage = if (response.weather.firstOrNull()?.main == "Rain") {
            R.drawable.panda_kehujanan
        } else if(response.weather.firstOrNull()?.main == "Clouds") {
            R.drawable.panda_turu
        } else{
            R.drawable.panda_minum_kelapa
        }

        val weatherIcon = when (response.weather.firstOrNull()?.main) {
            "Rain" -> R.drawable.cloud
            "Clouds" -> R.drawable.cloud
            "Clear" -> R.drawable.cloud
            else -> R.drawable.cloud
        }

        val iconCode = response.weather.firstOrNull()?.icon
        val iconUrl = if (iconCode.isNullOrBlank()) {
            "" // Or a default placeholder URL
        } else {
            "https://openweathermap.org/img/wn/$iconCode@2x.png"
        }

        return WeatherData(
            location = "${response.city}, ${response.system.country}",
            date = formatDate(Date().time / 1000),
            updatedTime = "Updated as of ${formatTime(System.currentTimeMillis() / 1000)}",
            weatherIconUrl = iconUrl,
            weatherStatus = response.weather.firstOrNull()?.main ?: "Unknown",
            temp = "${kelvinToCelsius(response.main.temperature).toInt()}°C",
            pandaImgRes = pandaImage,
            humidity = "${response.main.humidity}%",
            wind = "${response.wind.speed.toInt()} m/s",
            feelsTemp = "${kelvinToCelsius(response.main.feelsLike).toInt()}°",
            rainFall = "${response.rain?.volume ?: "-"}",
            pressure = "${response.main.pressure} hPa",
            cloudsPercentage = "${response.clouds?.cloudiness ?: "-"}",
            sunriseTime = formatTime(response.system.sunriseTime),
            sunsetTime = formatTime(response.system.sunsetTime)
        )
    }

    // Helper functions for formatting time
    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("MMMM dd", Locale.getDefault())
        return sdf.format(Date(timestamp * 1000))
    }

    private fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(Date(timestamp * 1000))
    }

    fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }
}