package com.taidev198.weatherapplication.utils.listener

import android.location.Location
import android.location.LocationListener

class MyLocationListener(
    private val onFetchLocation: OnFetchListener
) : LocationListener {
    override fun onLocationChanged(location: Location) {
        onFetchLocation.onDataLocation(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        // TODO implement later
    }

    override fun onProviderEnabled(provider: String) {
        // TODO implement later
    }

    override fun onProviderDisabled(provider: String) {
        // TODO implement later
    }
}