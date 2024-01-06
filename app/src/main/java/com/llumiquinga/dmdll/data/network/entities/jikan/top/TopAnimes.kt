package com.llumiquinga.dmdll.data.network.entities.jikan.top

data class TopAnimes( //esta es la importante me devuelve los datos y la paginacion
    val `data`: List<Data>,
    val pagination: Pagination
)