package com.example.firsttestapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CityResponse(
    @SerialName("id") val id:Int? = null,
    @SerialName("origin") val origin:String? = null,
    @SerialName("origin_state") val originState: String? = null,
    @SerialName("destination") val destination: String? = null,
    @SerialName("destination_state") val destinationState: String? = null,
)
