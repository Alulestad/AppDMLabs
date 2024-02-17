package com.examenp.recliclerview.data.network.entities.nobelserializable


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Laureate(
    @SerialName("fullName")
    var fullName: FullName?=null,
    @SerialName("id")
    val id: String="",
    @SerialName("knownName")
    val knownName: KnownName= KnownName(""),
    @SerialName("links")
    val links: List<LinkX> = listOf(),
    @SerialName("motivation")
    val motivation: Motivation=Motivation(),
    @SerialName("nativeName")
    val nativeName: String="",
    @SerialName("orgName")
    val orgName: OrgName=OrgName("",""),
    @SerialName("portion")
    val portion: String="",
    @SerialName("sortOrder")
    val sortOrder: String=""
)