package com.example.firsttestapp.core.network

import com.example.firsttestapp.domain.model.CargoEntity
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import org.json.JSONObject
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException


fun extractMessage(text: String?): MetaResponse? {
    return try {
        if(text != null) {
            val obj = JSONObject(text)
            val meta = obj.getString("meta")

            return Json.decodeFromString<MetaResponse>(meta)
        }
        null
    } catch (e: Exception) {
        null
    }
}


//TODO : wrote for Mock data
suspend inline fun <reified X> safeApiCall(json: String): DataHolder<X> {
    val res = Json.decodeFromString<X>(json)
    return DataHolder.Success(res, null)
}


suspend inline fun <reified X> safeApiCall(crossinline call: suspend () -> HttpResponse): DataHolder<X> {
    val result = try {
        val response = call.invoke()
        if (response.status.value in 200 until 299) {
            val body = response.body<X>()
            body?.let {
                DataHolder.Success(body, null)
            } ?: run { DataHolder.Error(response.status.value, extractMessage(
                response.bodyAsText()
            )?.map()) }
        }
        else
            DataHolder.Error(response.status.value,extractMessage(
                response.bodyAsText()
            )?.map())
    } catch (e: java.lang.Exception) {
        println(e)

        if(e is CancellationException)
            throw java.util.concurrent.CancellationException()

        if (e is HttpException) {
            DataHolder.Error(e.code(), MetaResponse("Network Error").map())
        } else {
            DataHolder.Error(-1, MetaResponse("Network Error").map())
        }
    }

    return result
}
