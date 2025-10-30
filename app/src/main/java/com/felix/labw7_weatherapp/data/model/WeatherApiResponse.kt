package com.felix.labw7_weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(
    @SerializedName("coord") val coordinates: Coordinates,
    @SerializedName("weather") val weather: List<WeatherDescription>,
    @SerializedName("main") val main: MainDetails,
    @SerializedName("wind") val wind: WindDetails,
    @SerializedName("sys") val system: SystemDetails,
    @SerializedName("name") val city: String,
    @SerializedName("cloud") val clouds: CloudsDetails?= null,
    @SerializedName("rain") val rain: RainVolume? = null
)

data class MainDetails(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int
)

data class WeatherDescription(
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class WindDetails(
    @SerializedName("speed") val speed: Double
)

data class Coordinates(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)

data class SystemDetails(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunriseTime: Long,
    @SerializedName("sunset") val sunsetTime: Long
)

data class CloudsDetails(
    @SerializedName("all") val cloudiness: Int
)

data class RainVolume(
    @SerializedName("1h") val volume: Double,
    @SerializedName("3h") val volume3Hours: Double
)