package com.taidev198.weatherapplication.di

import com.taidev198.weatherapplication.data.repository.source.WeatherDataSource
import com.taidev198.weatherapplication.data.repository.source.local.WeatherLocalDataSource
import com.taidev198.weatherapplication.data.repository.source.remote.WeatherRemoteDataSource
import com.taidev198.weatherapplication.utils.Constant.BASE_API_SERVICE
import com.taidev198.weatherapplication.utils.Constant.PRO_API_SERVICE
import io.reactivex.schedulers.Schedulers.single
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DataSourceModule =
    module {
        single<WeatherDataSource.Remote> {
            WeatherRemoteDataSource(
                baseApiService = get(named(BASE_API_SERVICE)),
                proApiService = get(named(PRO_API_SERVICE)),
            )
        }
            single<WeatherDataSource.Local> { WeatherLocalDataSource(get(), get()) }
    }