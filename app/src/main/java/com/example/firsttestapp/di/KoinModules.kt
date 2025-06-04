package com.example.firsttestapp.di

import com.example.firsttestapp.presentation.HomeScreen.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val koinModules = module {
    viewModelOf (::HomeViewModel)
}