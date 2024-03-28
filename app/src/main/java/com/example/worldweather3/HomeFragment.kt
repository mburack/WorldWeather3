package com.example.worldweather3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.worldweather3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cities = listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, cities)
        binding.cityAutoCompleteTextView.setAdapter(adapter)

        binding.searchButton.setOnClickListener {
            val city = binding.cityAutoCompleteTextView.text.toString()
            if (city.isNotBlank()) {
                displayWeatherFragment(city)
            } else {
                Toast.makeText(context, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayWeatherFragment(city: String) {
        val fragment = CurrentWeatherFragment().apply {
            arguments = Bundle().apply {
                putString("city", city)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
