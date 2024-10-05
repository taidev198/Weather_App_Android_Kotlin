package com.taidev198.weatherapplication.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.taidev198.weatherapplication.R
import com.taidev198.weatherapplication.base.BaseActivity
import com.taidev198.weatherapplication.databinding.ActivityMainBinding
import com.taidev198.weatherapplication.notification.DailyWorker
import com.taidev198.weatherapplication.ui.favourite.FavouriteFragment
import com.taidev198.weatherapplication.ui.home.HomeFragment
import com.taidev198.weatherapplication.utils.Constant
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.concurrent.TimeUnit

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
        setNavigation()
        scheduleDailyWorkAtSpecificTime(23, 46)
    }

    private fun setNextFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(fragment::javaClass.name)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun setNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mi_home -> setNextFragment(HomeFragment.newInstance())
                R.id.mi_favorite -> setNextFragment(FavouriteFragment.newInstance())
            }
            true
        }
    }

    private fun scheduleDailyWorkAtSpecificTime(hour: Int, minute: Int) {
        val currentTime = Calendar.getInstance()

        val targetTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (before(currentTime)) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        val initialDelay = targetTime.timeInMillis - currentTime.timeInMillis

        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val myRequest = PeriodicWorkRequest.Builder(
            DailyWorker::class.java,
            24,
            TimeUnit.HOURS
        ).setConstraints(constraints)
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(this).cancelUniqueWork(Constant.DAILY_WORK_MANAGER_ID)
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            Constant.DAILY_WORK_MANAGER_ID,
            ExistingPeriodicWorkPolicy.REPLACE,
            myRequest
        )
    }

}