package com.example.firsttestapp.core.network

fun MetaResponse.map(): MetaEntity {
    return MetaEntity(
        message = this.message,
        errors = this.error?.map { it.map() }
    )
}

fun ErrorResponse.map(): ErrorEntity {
    return ErrorEntity(
        field = this.field,
        messages = this.messages
    )
}