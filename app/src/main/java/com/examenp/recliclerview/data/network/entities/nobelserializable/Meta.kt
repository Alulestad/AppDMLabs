package com.examenp.recliclerview.data.network.entities.nobelserializable


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("count")
    val count: Int,
    @SerialName("disclaimer")
    val disclaimer: String,
    @SerialName("license")
    val license: String,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("terms")
    val terms: String
)