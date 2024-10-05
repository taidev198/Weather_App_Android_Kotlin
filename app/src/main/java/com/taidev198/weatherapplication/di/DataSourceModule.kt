package com.taidev198.weatherapplication.di

import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.local.WeatherLocalDataSource
import com.taidev198.weatherapplication.data.repository.source.remote.WeatherRemoteDataSource
import io.reactivex.schedulers.Schedulers.single
import org.koin.dsl.module

val DataSourceModule =
    module {
            single<WeatherDataSource.Remote> { WeatherRemoteDataSource(get()) }
            single<WeatherDataSource.Local> { WeatherLocalDataSource(get()) }
    }