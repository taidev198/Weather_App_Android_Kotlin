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
        arguments?.let {
            latitude = it.getDouble(ARG_LATITUDE)
            longitude = it.getDouble(ARG_LONGITUDE)
        }
    }

    override fun initView() {
        binding.icLocation.setOnClickListener {
            fetchWeatherData()
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
        // TODO LATER
    }

    private fun fetchWeatherData() {
        viewModel.fetchCurrentWeather(latitude, longitude)
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

        fun newInstance(
            latitude: Double,
            longitude: Double,
        ): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putDouble(ARG_LATITUDE, latitude)
            args.putDouble(ARG_LONGITUDE, longitude)
            fragment.arguments = args
            return fragment
        }

        private fun formatDate(timestamp: Long): String {
            val date = Date(timestamp * SECOND_TO_MILLIS)
            return SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date)
        }

        private const val SECOND_TO_MILLIS = 1000
        private const val DATE_PATTERN = ", dd MMMM"
    }
}