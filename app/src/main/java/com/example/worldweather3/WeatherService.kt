package com.example.worldweather3.network

import com.example.worldweather3.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Call<WeatherResponse>
}
