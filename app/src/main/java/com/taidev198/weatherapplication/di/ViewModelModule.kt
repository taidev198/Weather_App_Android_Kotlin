package com.taidev198.weatherapplication.di

import com.taidev198.weatherapplication.ui.DetailViewModel
import com.taidev198.weatherapplication.ui.FavouriteViewModel
import com.taidev198.weatherapplication.ui.HomeViewModel
import com.taidev198.weatherapplication.ui.MainViewModel
import com.taidev198.weatherapplication.ui.SearchViewModel
import com.taidev198.weatherapplication.ui.SharedViewModel
import com.taidev198.weatherapplication.ui.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ViewModelModule: Module =
    module {
        viewModel { MainViewModel(get()) }
        viewModel { SharedViewModel() }
        viewModel { HomeViewModel(get(), get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { DetailViewModel(get()) }
        viewModel { FavouriteViewModel(get()) }
        viewModel { SettingViewModel() }
}