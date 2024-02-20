package com.examenp.recliclerview.data.network.entities.nobel

import kotlinx.serialization.Serializable


@Serializable
data class NobelPrize(
    val links: Links?=null,
    val meta: Meta?=null,
    val nobelPrizes: List<NobelPrizeX>?= emptyList()
)