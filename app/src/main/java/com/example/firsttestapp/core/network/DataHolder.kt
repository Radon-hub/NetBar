package com.example.firsttestapp.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class DataHolder<out T>(val meta: MetaEntity?) {
    class Success<T>(val data: T, meta: MetaEntity?): DataHolder<T>(meta)
    class Error(val data: Int, meta: MetaEntity?): DataHolder<Nothing>(meta)
}

data class MetaEntity(
    val message: String,
    val errors: List<ErrorEntity>? = null
)

data class ErrorEntity(
    val field: String,
    val messages: List<String>
)

@Serializable
data class MetaResponse(
    @SerialName("message")
    val message: String,

    @SerialName("errors")
    val error: List<ErrorResponse>? = null
)

@Serializable
data class ErrorResponse(
    @SerialName("field")
    val field: String,

    @SerialName("messages")
    val messages: List<String>
)