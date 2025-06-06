package com.example.firsttestapp.data.mapper

import com.example.firsttestapp.data.model.CargoResponse
import com.example.firsttestapp.data.model.CityResponse
import com.example.firsttestapp.domain.model.CargoEntity
import com.example.firsttestapp.domain.model.CityEntity


fun CargoResponse.map() = CargoEntity(
    id = this.id,
    city = this.city?.map(),
    weight = this.weight,
    price = this.price,
    isSelected = this.isSelected
)

fun CityResponse.map() = CityEntity(
    id = this.id,
    origin = this.origin,
    originState = this.originState,
    destination = this.destination,
    destinationState = this.destinationState
)