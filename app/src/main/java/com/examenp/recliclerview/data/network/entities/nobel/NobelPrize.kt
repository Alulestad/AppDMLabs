package com.examenp.recliclerview.data.network.entities.nobel


data class NobelPrize(
    val links: Links,
    val meta: Meta,
    val nobelPrizes: List<NobelPrizeX>
)