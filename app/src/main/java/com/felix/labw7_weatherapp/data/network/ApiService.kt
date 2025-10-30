package com.felix.labw7_weatherapp.data.network

import com.felix.labw7_weatherapp.data.model.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String
    ): WeatherApiResponse
}