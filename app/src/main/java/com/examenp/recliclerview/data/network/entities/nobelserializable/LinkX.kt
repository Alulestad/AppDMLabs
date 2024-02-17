package com.examenp.recliclerview.data.network.entities.nobelserializable


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkX(
    @SerialName("action")
    val action: String,
    @SerialName("href")
    val href: String,
    @SerialName("rel")
    val rel: String,
    @SerialName("types")
    val types: String
)