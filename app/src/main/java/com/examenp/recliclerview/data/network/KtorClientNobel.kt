package com.examenp.recliclerview.data.network

import com.examenp.recliclerview.data.network.entities.nobelserializable.NobelPrizeS
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClientNobel {
    private val client = HttpClient(OkHttp) {
        defaultRequest { url("http://api.nobelprize.org/2.1/") } //para las solicitudes http

        install(Logging) { //permite que se registren en un log las solicitudes
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation) { //converte las respuesta en objetos kotlin
            json(Json {//se configura para Json
                ignoreUnknownKeys = true //si las claves no son corespodientes se ignoran
            })
        }
    }


    suspend fun getAllNobelPrizes(limit: Int): ApiOperation<NobelPrizeS> {
        return safeApiCall {
            client.get("nobelPrizes?limit=$limit") //realiza una solicitud get
                .body<NobelPrizeS>() //obtiene el cuerpo de respuesta
                 //lo comvierte a un dominio


        }
    }







    private inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
        //inline, Kotlin “pegará” el código de la función en cada lugar donde se llame,
        // en lugar de invocar la función de la manera habitual
        //
        return try {
            ApiOperation.Success(data = apiCall()) //si es correcto envia la apiCall (es como un Supplier la apiCall)
        } catch (e: Exception) {
            ApiOperation.Failure(exception = e)
        }
    }
}

