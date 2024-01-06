package com.llumiquinga.dmdll.core

import com.llumiquinga.dmdll.data.network.entities.jikan.anime.FullInfoAnime
import com.llumiquinga.dmdll.logic.usercase.jikan.entities.FullInfoAnimeLG

class DataClassExtensions {

    fun FullInfoAnime.getFullInfoAnimeLG(): FullInfoAnimeLG {
        val a= FullInfoAnimeLG()

        a.id=this.data.mal_id
        a.name=this.data.title_english
        a.small_image=this.data.images.jpg.small_image_url
        a.big_image=this.data.images.jpg.large_image_url
        a.synopsis=this.data.synopsis

        return a
    }
}