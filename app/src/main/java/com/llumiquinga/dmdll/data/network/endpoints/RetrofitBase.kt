package com.llumiquinga.dmdll.data.network.endpoints

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    private const val JIKAN_URL="https://api.jikan.moe/v4/"
    fun getRetrofitJikanConnection():Retrofit{ //ESTO ES UNCAMENTE UNA CONNECCION
        // (menoa de la mitad de camino del diagrama)
        return Retrofit.Builder().baseUrl(JIKAN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}