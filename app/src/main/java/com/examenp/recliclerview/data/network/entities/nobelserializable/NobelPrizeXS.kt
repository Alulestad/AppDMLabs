package com.examenp.recliclerview.data.network.entities.nobelserializable


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NobelPrizeXS(
    @SerialName("awardYear")
    val awardYear: String,
    @SerialName("category")
    val category: Category,
    @SerialName("categoryFullName")
    val categoryFullName: CategoryFullName,
    @SerialName("dateAwarded")
    val dateAwarded: String,
    @SerialName("laureates")
    val laureates: List<Laureate>,
    @SerialName("links")
    val links: List<LinkX>,
    @SerialName("prizeAmount")
    val prizeAmount: Int,
    @SerialName("prizeAmountAdjusted")
    val prizeAmountAdjusted: Int
)