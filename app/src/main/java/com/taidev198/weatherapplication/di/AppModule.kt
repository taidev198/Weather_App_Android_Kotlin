package com.taidev198.weatherapplication.di

import android.app.Application
import android.content.res.Resources
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.taidev198.weatherapplication.data.repository.source.remote.api.middleware.BooleanAdapter
import com.taidev198.weatherapplication.data.repository.source.remote.api.middleware.DoubleAdapter
import com.taidev198.weatherapplication.data.repository.source.remote.api.middleware.IntegerAdapter
import com.taidev198.weatherapplication.utils.DateTimeUtils
import com.taidev198.weatherapplication.utils.dispatchers.BaseDispatcherProvider
import com.taidev198.weatherapplication.utils.dispatchers.DispatcherProvider
import org.koin.dsl.module

val AppModule =
    module {
        single { provideResources(get()) }

        single { provideBaseDispatcherProvider() }

        single { provideGson() }
}

fun provideResources(app: Application): Resources {
    return app.resources
}

fun provideBaseDispatcherProvider(): BaseDispatcherProvider {
    return DispatcherProvider()
}

fun provideGson(): Gson {
    val booleanAdapter = BooleanAdapter()
    val integerAdapter = IntegerAdapter()
    val doubleAdapter = DoubleAdapter()
    return GsonBuilder()
        .registerTypeAdapter(Boolean::class.java, booleanAdapter)
        .registerTypeAdapter(Int::class.java, integerAdapter)
        .registerTypeAdapter(Double::class.java, doubleAdapter)
        .setDateFormat(DateTimeUtils.DATE_TIME_FORMAT_UTC)
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()
}