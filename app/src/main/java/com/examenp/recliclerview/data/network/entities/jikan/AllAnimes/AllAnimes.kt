package com.examenp.recliclerview.data.network.entities.jikan.AllAnimes

import com.examenp.recliclerview.logic.network.entities.FullInfoAnimeLG

data class AllAnimes(
    val `data`: List<Data>,
    val pagination: Pagination
)

fun AllAnimes.getAllAnime(): FullInfoAnimeLG {
    val a= FullInfoAnimeLG()

    a.id=this.data.get(1).mal_id
    a.name=this.data.get(1).title_english
    a.small_image=this.data.get(1).images.jpg.small_image_url
    a.big_image=this.data.get(1).images.jpg.large_image_url
    a.synopsis=this.data.get(1).synopsis

    return a
}