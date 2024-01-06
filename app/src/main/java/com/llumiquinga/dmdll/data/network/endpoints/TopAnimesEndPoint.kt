package com.llumiquinga.dmdll.data.network.endpoints

import com.llumiquinga.dmdll.data.network.entities.jikan.anime.FullInfoAnime
import com.llumiquinga.dmdll.data.network.entities.jikan.top.TopAnimes
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface TopAnimesEndPoint {


    @GET("v4/top/anime")
    fun getAllTopAnimes():Response<List<TopAnimes>>
    //response devuelve la cabezera y el cuerpo.



}