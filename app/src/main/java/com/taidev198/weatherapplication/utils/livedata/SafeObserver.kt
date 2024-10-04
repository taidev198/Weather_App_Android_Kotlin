package com.taidev198.weatherapplication.utils.livedata

import androidx.lifecycle.Observer

class SafeObserver<T>(private val notifier: (T) -> Unit) : Observer<T> {
    override fun onChanged(value: T) {
        notifier(value)
    }
}