package com.examenp.recliclerview.data.network.entities.nobel

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val en: String?="",
    val no: String?="",
    val se: String?=""
)