package com.examenp.recliclerview.data.network.repository

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorApiModule {


    fun getKtorHttpClient()  = HttpClient(Android) {
        expectSuccess = true
        engine {
            connectTimeout = 15000
            socketTimeout = 15000
        }
        // Logging
        install(Logging) {
            level = LogLevel.ALL
        }
        // JSON
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                useAlternativeNames = true
                ignoreUnknownKeys = true
                encodeDefaults = false
            })
        }

        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 1000L * 300L
            connectTimeoutMillis = 1000L * 120L
            socketTimeoutMillis = 1000L * 120L
        }
        // Response observer
        install(ResponseObserver) {
            onResponse { response ->
                Log.d("TAG", response.request.content.status.toString()) //manda la respuesta de esto de aki
            }
        }
        // Apply to all requests
        // Add headers if your endpoint requires
        defaultRequest {
            /*
            KtorNetworkApi.headerMap.apply {
                if (!isNullOrEmpty()){
                    forEach{
                        header(it.key,it.value)
                    }
                }
            }
            //No estamos usando llaves ni nada en las cabeceras, asi que lo retiro
             */
            // Content Type
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
        }
    }

}