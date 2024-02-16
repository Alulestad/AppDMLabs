package com.examenp.recliclerview.data.network.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    private const val JIKAN_URL="https://api.jikan.moe/v4/"
    private const val NOBEL_PRIZES="http://api.nobelprize.org/2.1/"
    fun getRetrofitJikanConnection():Retrofit{ //ESTO ES UNCAMENTE UNA CONNECCION
        // (menoa de la mitad de camino del diagrama)
        return Retrofit.Builder().baseUrl(JIKAN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getNobelPrizesConnection():Retrofit{ //ESTO ES UNCAMENTE UNA CONNECCION
        // (menoa de la mitad de camino del diagrama)
        return Retrofit.Builder().baseUrl(NOBEL_PRIZES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}