package com.example.firsttestapp.core.di

import com.example.firsttestapp.core.network.KtorService
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val NetworkModules = module {
    single<HttpClientEngine> { OkHttp.create() }
    single { KtorService(get()) }
}