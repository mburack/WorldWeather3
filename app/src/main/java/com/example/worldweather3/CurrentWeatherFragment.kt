package com.example.worldweather3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.worldweather3.databinding.FragmentCurrentWeatherBinding
import com.example.worldweather3.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val city = arguments?.getString("city") ?: "Blank" //No data if no city
        fetchCurrentWeather(city)
    }

    private fun fetchCurrentWeather(city: String) {
        RetrofitInstance.api.getCurrentWeather("5117bbe36966493ca8301014242703", city).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { weatherResponse ->
                        binding.temperatureText.text = getString(R.string.temperature_template, weatherResponse.current.temp_c)
                        binding.humidityText.text = getString(R.string.humidity_template, weatherResponse.current.humidity)
                        binding.conditionText.text = weatherResponse.current.condition.text
                    }
                } else {
                    Log.e("CurrentWeatherFragment", "Response not successful")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("CurrentWeatherFragment", "Error fetching weather", t)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
