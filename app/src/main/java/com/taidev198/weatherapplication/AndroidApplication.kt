package com.taidev198.weatherapplication

import android.app.Application
import com.taidev198.weatherapplication.di.AppModule
import com.taidev198.weatherapplication.di.DataSourceModule
import com.taidev198.weatherapplication.di.NetworkModule
import com.taidev198.weatherapplication.di.RepositoryModule
import com.taidev198.weatherapplication.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class AndroidApplication : Application() {
    private val rootModule =
        listOf(AppModule, NetworkModule, DataSourceModule, RepositoryModule, ViewModelModule)
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@AndroidApplication)
            androidFileProperties()
            modules(rootModule)
        }
    }
}