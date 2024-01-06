package com.llumiquinga.dmdll.core

import com.llumiquinga.dmdll.data.network.entities.jikan.anime.FullInfoAnime
import com.llumiquinga.dmdll.logic.usercase.jikan.entities.FullInfoAnimeLG



    fun FullInfoAnime.getFullInfoAnimeLG()= FullInfoAnimeLG (
        this.data.mal_id,
        this.data.title_english,
        this.data.images.jpg.small_image_url,
        this.data.images.jpg.large_image_url,
        this.data.synopsis

)
