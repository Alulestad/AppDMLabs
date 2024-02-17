package com.examenp.recliclerview.data.network.models.remote

import kotlinx.serialization.Serializable

/*
@Serializable
data class RemoteNobel( //seria como mi json original
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: String,
    val characters: List<String>
)

fun RemoteNobel.toDomainEpisode(): Episode { //seria como un adaptador
    return Episode(
        id = id,
        name = name,
        seasonNumber = episode.filter { it.isDigit() }.take(2).toInt(),
        episodeNumber = episode.filter { it.isDigit() }.takeLast(2).toInt(),
        airDate = air_date,
        characterIdsInEpisode = characters.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1).toInt()
        }
    )
}


 */

//Esto hera para hacer un especie de adapter pero en verdad nunca se lo ocupo