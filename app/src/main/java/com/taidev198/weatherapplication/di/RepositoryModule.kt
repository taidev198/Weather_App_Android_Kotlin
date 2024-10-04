package com.taidev198.weatherapplication.di

import com.taidev198.weatherapplication.data.repository.WeatherRepositoryImpl
import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.WeatherRepository
import com.taidev198.weatherapplication.data.repository.source.local.WeatherLocalDataSource
import com.taidev198.weatherapplication.data.repository.source.remote.WeatherRemoteDataSource
import io.reactivex.schedulers.Schedulers.single
import org.koin.dsl.module

val RepositoryModule = module {
    single {
        provideWeatherRepository(
            WeatherLocalDataSource(get()),
            WeatherRemoteDataSource(get())
        )
    }
}

fun provideWeatherRepository(
    local: WeatherDataSource.Local,
    remote: WeatherDataSource.Remote
): WeatherRepository {
    return WeatherRepositoryImpl(local, remote)
}