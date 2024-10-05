package com.taidev198.weatherapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.taidev198.weatherapplication.R
import com.taidev198.weatherapplication.base.BaseFragment
import com.taidev198.weatherapplication.data.model.entity.WeatherEntity
import com.taidev198.weatherapplication.databinding.FragmentHomeBinding
import com.taidev198.weatherapplication.ui.HomeViewModel
import com.taidev198.weatherapplication.ui.SharedViewModel
import com.taidev198.weatherapplication.ui.detail.DetailFragment
import com.taidev198.weatherapplication.ui.search.SearchFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModel()
    override val sharedViewModel: SharedViewModel by activityViewModel()
    private var cityName: String? = null
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.latitude.value?.let { latitude = it }
        sharedViewModel.longitude.value?.let { longitude = it }
        fetchWeatherData()
    }

    override fun initView() {
        binding.icLocation.setOnClickListener {
            fetchWeatherData()
        }
        binding.tvLocation.setOnClickListener {
            replaceFragment(R.id.fragment_container, SearchFragment.newInstance(), true)
        }
        binding.icArrowDown.setOnClickListener {
            sharedViewModel.selectedLocation.observe(viewLifecycleOwner) { location ->
                location?.let {
                    fetchWeatherForLocation(it, "vi")
                }
            }
        }
        binding.constraintLayout.setOnClickListener {
            cityName?.let {
                replaceFragment(R.id.fragment_container, DetailFragment.newInstance(it), true)
            } ?: run {
                Toast.makeText(context, "City name is not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initData() {
        fetchWeatherData()
        sharedViewModel.isNetworkAvailable.observe(viewLifecycleOwner) { isAvailable ->
            if (isAvailable) {
                fetchWeatherData()
            }
        }
        viewModel.currentWeather.observe(viewLifecycleOwner) { weather ->
            weather?.let {
                updateUIWithCurrentWeather(it)
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            showError(errorMessage)
        }
    }

    override fun bindData() {
        sharedViewModel.latitude.observe(this) { lat ->
            lat?.let { latitude = it }
        }
        sharedViewModel.longitude.observe(this) { lon ->
            lon?.let { longitude = it }
        }
        sharedViewModel.selectedLocation.observe(viewLifecycleOwner) { location ->
            location?.let {
                fetchWeatherForLocation(it, "vi")
            }
        }
    }

    private fun fetchWeatherData() {
        viewModel.fetchCurrentWeather(latitude, longitude, "vi")
    }
    private fun fetchWeatherForLocation(
        location: String,
        language: String,
    ) {
        viewModel.fetchWeatherForSearchResult(location, "vi")
    }

    private fun updateUIWithCurrentWeather(currentWeather: WeatherEntity) {
        Log.v("LCD", currentWeather.toString())
        binding.tvLocation.text = getString(R.string.city_name, currentWeather.city, currentWeather.country)
        cityName = currentWeather.city
        binding.tvCurrentDay.text = getString(R.string.today) + formatDate(currentWeather.timeZone!!)
        binding.tvCurrentTemperature.text = "${currentWeather.weatherCurrent?.currentTemperature?.roundToInt()}°C"
        binding.tvCurrentText.text = currentWeather.weatherCurrent?.weatherDescription
        binding.tvCurrentPercentCloud.text = currentWeather.weatherCurrent?.windSpeed.toString()
        binding.tvCurrentHumidity.text = currentWeather.weatherCurrent?.humidity.toString()
        binding.tvCurrentPercentCloud1.text = currentWeather.weatherCurrent?.percentCloud.toString()
        Glide.with(this).load(currentWeather.weatherCurrent?.iconWeather).into(binding.ivCurrentWeather)
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val SELECTED_LOCATION = "selected_location"
        const val ARG_LATITUDE = "latitude"
        const val ARG_LONGITUDE = "longitude"

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }

        private fun formatDate(timestamp: Long): String {
            val date = Date(timestamp * SECOND_TO_MILLIS)
            return SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date)
        }

        private const val SECOND_TO_MILLIS = 1000
        private const val DATE_PATTERN = ", dd MMMM"
    }
}