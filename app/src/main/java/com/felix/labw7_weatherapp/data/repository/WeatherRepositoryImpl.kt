package com.felix.labw7_weatherapp.data.repository

import com.felix.labw7_weatherapp.data.model.WeatherApiResponse
import com.felix.labw7_weatherapp.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService // Hilt gives us the ApiService
) : WeatherRepository {

    override fun getWeather(city: String): Flow<Result<WeatherApiResponse>> = flow {
        try {
            // Call the ApiService (provided by NetworkModule)
            val response = apiService.getWeather(city)
            emit(Result.success(response))
        } catch (e: Exception) {
            // Emit the error
            emit(Result.failure(e))
        }
    }
}