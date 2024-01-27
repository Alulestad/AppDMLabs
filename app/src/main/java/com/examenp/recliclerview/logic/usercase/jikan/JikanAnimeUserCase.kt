package com.examenp.recliclerview.logic.usercase.jikan

import android.util.Log
import com.examenp.recliclerview.data.network.endpoints.AnimeEndPoint
import com.examenp.recliclerview.data.network.entities.jikan.anime.getFullInfoAnimeLG
import com.examenp.recliclerview.data.network.repository.RetrofitBase
import com.examenp.recliclerview.logic.entities.FullInfoAnimeLG

import com.examenp.recliclerview.core.Constants

class JikanAnimeUserCase {

    fun getFullAnimeInfo(nameAnime:Int): FullInfoAnimeLG {

        var infoAnime: FullInfoAnimeLG = FullInfoAnimeLG()

        try {
        val baseService= RetrofitBase.getRetrofitJikanConnection()
        val service= baseService.create(AnimeEndPoint::class.java) //creo mi servicio
        val call= service.getAnimeFullInfo(nameAnime) //ahora si podria acceder a travez de servicio a los metodos
        //me revuelve un response de FullInfoAnime


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
        }catch (ex:Exception){
            Log.e(Constants.TAG, ex.stackTraceToString())
        };
        return infoAnime
    }

    fun getAllTopsAnimes(){

        //return List<FullInfoAnimeLG>() //"Listadod de animes"
    }

}