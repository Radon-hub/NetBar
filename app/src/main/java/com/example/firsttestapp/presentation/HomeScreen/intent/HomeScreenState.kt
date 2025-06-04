package com.example.firsttestapp.presentation.HomeScreen.intent

import com.example.firsttestapp.domain.model.CargoEntity

data class HomeScreenState(
    val cargoList: List<CargoEntity> = emptyList(),
    val selectedCargo: CargoEntity? = null,
    val showSheet: Boolean = false
)
