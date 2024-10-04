package com.taidev198.weatherapplication.utils.listener

import android.location.Location

interface OnFetchListener {
    fun onDataLocation(location: Location?)
}
