package com.example.firsttestapp.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class KtorService(
    private val engine: HttpClientEngine
) {

    private val baseUrl = "HASSIN_TARABARNET_API"

    private val _client = run {
        HttpClient(engine = engine) {
            install(ContentNegotiation) {
                json(json = Json {
                    explicitNulls = true
                    ignoreUnknownKeys = true
                    isLenient = true
                    coerceInputValues = true
                })
            }

            // TODO : We set this for logging API requests

//            install(Logging) {
//                logger = object : Logger {
//                    override fun log(message: String) {
//                        println(message)
//                    }
//                }
//                level = LogLevel.ALL
//            }

            defaultRequest {

                url {
                    this.protocol =  URLProtocol.HTTPS
                    host = baseUrl
                }

                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun client(block: suspend HttpClient.() -> HttpResponse): HttpResponse {
        return block.invoke(_client)
    }

}