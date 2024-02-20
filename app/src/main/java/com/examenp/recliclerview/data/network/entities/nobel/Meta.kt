package com.examenp.recliclerview.data.network.entities.nobel

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val count: Int?=-1,
    val disclaimer: String?="",
    val license: String?="",
    val limit: Int?=-1,
    val offset: Int?=-1,
    val terms: String?=""
)