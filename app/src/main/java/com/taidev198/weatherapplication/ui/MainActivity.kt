package com.taidev198.weatherapplication.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.taidev198.weatherapplication.R
import com.taidev198.weatherapplication.base.BaseActivity
import com.taidev198.weatherapplication.databinding.ActivityMainBinding
import com.taidev198.weatherapplication.ui.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModel()
    override val sharedViewModel: SharedViewModel by viewModel()

    override fun initialize() {
        viewModel.locationData.observe(
            this,
            Observer { location ->
                val (latitude, longitude) = location
                //pass location to homefragment
                sharedViewModel.setLocation(latitude, longitude)
                val homeFragment = HomeFragment.newInstance()
                setNextFragment(homeFragment)
            },
        )

        //call function that requires user's location
        viewModel.requestLocationAndFetchWeather(this)
    }

    private fun setNextFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(fragment::javaClass.name)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}