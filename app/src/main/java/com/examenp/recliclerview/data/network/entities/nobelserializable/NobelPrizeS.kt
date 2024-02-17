package com.examenp.recliclerview.data.network.entities.nobelserializable


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NobelPrizeS(
    @SerialName("links")
    val links: Links,
    @SerialName("meta")
    val meta: Meta,
    @SerialName("nobelPrizes")
    val nobelPrizeXS: List<NobelPrizeXS>
)