package com.llumiquinga.dmdll.data.network.entities.jikan.top

data class TopAnime(
    val `data`: List<Data> = listOf(),
    val pagination: Pagination?=null
)