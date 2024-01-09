package com.llumiquinga.dmdll.data.network.endpoints

import com.llumiquinga.dmdll.data.network.entities.jikan.AllAnimes.AllAnimes
import com.llumiquinga.dmdll.data.network.entities.jikan.anime.FullInfoAnime
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimesSearchEndPoint {


    @GET("v4/anime")
    fun getAllAnimes():Response<List<AllAnimes>>
    //response devuelve la cabezera y el cuerpo.



}