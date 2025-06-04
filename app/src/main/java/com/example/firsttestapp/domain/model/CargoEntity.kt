package com.example.firsttestapp.domain.model

data class CargoEntity(
    val id:Int? = null,
    val city: CityEntity? = null,
    val weight: Float? = null,
    val price: Float? = null,
    val isSelected: Boolean? = null,
)
