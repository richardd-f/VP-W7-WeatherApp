package com.felix.labw7_weatherapp.data.repository

import com.felix.labw7_weatherapp.data.model.WeatherApiResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(city: String): Flow<Result<WeatherApiResponse>>
}