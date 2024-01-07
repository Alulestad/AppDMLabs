package com.llumiquinga.dmdll.data.network.entities.jikan.anime

import com.llumiquinga.dmdll.logic.entities.FullInfoAnimeLG

data class FullInfoAnime(
    val `data`: Data
)


fun FullInfoAnime.getFullInfoAnimeLG(): FullInfoAnimeLG {
    val a= FullInfoAnimeLG()

    a.id=this.data.mal_id
    a.name=this.data.title_english
    a.small_image=this.data.images.jpg.small_image_url
    a.big_image=this.data.images.jpg.large_image_url
    a.synopsis=this.data.synopsis

    return a
}