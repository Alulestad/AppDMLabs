package com.examenp.recliclerview.data.network.entities.nobel

import kotlinx.serialization.Serializable

@Serializable
data class NobelPrizeX(
    val awardYear: String?="",
    val category: Category?=null,
    val categoryFullName: CategoryFullName?=null,
    val dateAwarded: String?="",
    val laureates: List<Laureate>?= emptyList(),
    val links: List<LinkX>?= emptyList(),
    val prizeAmount: Int?=-1,
    val prizeAmountAdjusted: Int?=-1
)

