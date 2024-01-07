package com.llumiquinga.dmdll.logic.usercase.jikan

import android.util.Log
import com.llumiquinga.dmdll.data.network.endpoints.AnimeEndPoint
import com.llumiquinga.dmdll.data.network.entities.jikan.anime.getFullInfoAnimeLG
import com.llumiquinga.dmdll.data.network.repository.RetrofitBase
import com.llumiquinga.dmdll.logic.entities.FullInfoAnimeLG

import com.llumiquinga.dmdll.ui.core.Constants

class JikanAnimeUserCase {

    fun getFullAnimeInfo(nameAnime:Int): FullInfoAnimeLG {
        val baseService= RetrofitBase.getRetrofitJikanConnection()
        val service= baseService.create(AnimeEndPoint::class.java) //creo mi servicio
        val call= service.getAnimeFullInfo(nameAnime) //ahora si podria acceder a travez de servicio a los metodos
        //me revuelve un response de FullInfoAnime

        var infoAnime: FullInfoAnimeLG = FullInfoAnimeLG()
        if(call.isSuccessful){

            val a=call.body()!!
            infoAnime=a.getFullInfoAnimeLG()


            /* //esto ya no se ocupa delego estas lineas a otro método
            infoAnime.id=call.body()!!.data.mal_id
            infoAnime.name=call.body()!!.data.title_english
            infoAnime.small_image=call.body()!!.data.images.jpg.small_image_url
            infoAnime.big_image=call.body()!!.data.images.jpg.large_image_url
*/


        }else{
            Log.d(Constants.TAG, "Error en el llamado a la API de Jikan")
        }

        return infoAnime
    }

    fun getAllTopsAnimes(){

        //return List<FullInfoAnimeLG>() //"Listadod de animes"
    }

}