package com.examenp.recliclerview.data.network.entities.jikan.top

data class TopAnime(
    val `data`: List<Data> = listOf(),
    val pagination: Pagination?=null
)