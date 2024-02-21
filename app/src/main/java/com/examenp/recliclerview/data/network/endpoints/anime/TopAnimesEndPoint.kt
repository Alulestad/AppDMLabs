package com.examenp.recliclerview.data.network.endpoints.anime

import com.examenp.recliclerview.data.network.entities.jikan.top.TopAnime
import retrofit2.Response
import retrofit2.http.GET

interface TopAnimesEndPoint {


    @GET("top/anime/") //v4/top/anime        top/anime
    suspend fun getAllTopAnimes():Response<TopAnime>
    //response devuelve la cabezera y el cuerpo.

    //devuelve una lista de animes y no necesito ningun parametro



}