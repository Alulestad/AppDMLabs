package com.examenp.recliclerview.data.network.entities.nobel

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val first: String?="",
    val last: String?="",
    val next: String?="",
    val self: String?=""
)