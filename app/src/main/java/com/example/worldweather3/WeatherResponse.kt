package com.example.worldweather3


data class WeatherResponse(
    val location: Location,
    val current: Current,
    val condition: Condition
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val tz_id: String,
    val localtime_epoch: Long,
    val localtime: String
)

data class Current(
    val temp_c: Float,
    val condition: Condition,
    val wind_kph: Float,
    val humidity: Int,
    val feelslike_c: Float
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)
