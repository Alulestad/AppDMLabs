package com.examenp.recliclerview.data.network.entities.nobelserializable


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Motivation(
    @SerialName("en")
    val en: String?="",
    @SerialName("no")
    val no: String?="",
    @SerialName("se")
    val se: String?=""
)