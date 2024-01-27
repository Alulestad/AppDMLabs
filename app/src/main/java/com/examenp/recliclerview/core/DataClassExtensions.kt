package com.examenp.recliclerview.core

import com.examenp.recliclerview.logic.entities.FullInfoAnimeLG
import com.examenp.recliclerview.data.network.entities.jikan.top.Data
import com.examenp.recliclerview.data.network.entities.jikan.anime.FullInfoAnime




fun FullInfoAnime.getFullInfoAnimeLG()= FullInfoAnimeLG (
        this.data.mal_id,
        this.data.title_english,
        this.data.images.jpg.small_image_url,
        this.data.images.jpg.large_image_url,
        this.data.synopsis

)

public fun Data.getFullInfoAnimeLG()= FullInfoAnimeLG (
    this.mal_id,
    this.title_english,
    this.images.jpg.small_image_url,
    this.images.jpg.large_image_url,
    this.synopsis

)
