package com.example.firsttestapp.core.di

import com.example.firsttestapp.data.CargoRepositoryIMP
import com.example.firsttestapp.data.repository.CargoRepository
import com.example.firsttestapp.domain.usecase.GetCargoListUseCase
import com.example.firsttestapp.presentation.HomeScreen.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val koinModules = module {
    viewModelOf (::HomeViewModel)
    factory { GetCargoListUseCase(get<CargoRepository>()::getCargoList) }
    factory<CargoRepository> { CargoRepositoryIMP(get()) }
}