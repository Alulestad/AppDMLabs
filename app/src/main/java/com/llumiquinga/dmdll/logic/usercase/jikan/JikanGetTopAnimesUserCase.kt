package com.llumiquinga.dmdll.logic.usercase.jikan

import android.util.Log
import com.llumiquinga.dmdll.data.network.endpoints.AnimeEndPoint
import com.llumiquinga.dmdll.data.network.endpoints.TopAnimesEndPoint
import com.llumiquinga.dmdll.data.network.entities.jikan.anime.getFullInfoAnimeLG
import com.llumiquinga.dmdll.data.network.entities.jikan.top.TopAnime
import com.llumiquinga.dmdll.data.network.repository.RetrofitBase
import com.llumiquinga.dmdll.logic.entities.FullInfoAnimeLG

import com.llumiquinga.dmdll.ui.core.Constants

class JikanGetTopAnimesUserCase {

    suspend fun getResponse(): TopAnime {

        val result:Result<TopAnime>
        var infoAnime = TopAnime() //TopAnimesDataClass()

        try {
            val baseService= RetrofitBase.getRetrofitJikanConnection()
            val service= baseService.create(TopAnimesEndPoint::class.java) //creo mi servicio
            val call= service.getAllTopAnimes() //ahora si podria acceder a travez de servicio a los metodos


            if(call.isSuccessful){
                val a=call.body()!! //es una lista de top animes
                infoAnime=a

            }else{
                Log.d(Constants.TAG, "Error en el llamado a la API de Jikan")
            }
        }catch (ex:Exception){
            Log.e(Constants.TAG, ex.stackTraceToString())
        };
        return infoAnime
    }



}