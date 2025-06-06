package com.example.firsttestapp.data.model

import com.example.firsttestapp.domain.model.CityEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CargoResponse(
    @SerialName("id") val id:Int? = null,
    @SerialName("city") val city: CityResponse? = null,
    @SerialName("weight") val weight: Float? = null,
    @SerialName("price") val price: Float? = null,
    @SerialName("is_selected") val isSelected: Boolean? = null,
)
