package com.examenp.recliclerview.data.network.entities.nobel

import kotlinx.serialization.Serializable

@Serializable
data class Laureate(
    val fullName: FullName?=null,
    val id: String?="",
    val knownName: KnownName?=null,
    val links: List<LinkX>?= emptyList(),
    val motivation: Motivation?=null,
    val nativeName: String?="",
    val orgName: OrgName?=null,
    val portion: String?="",
    val sortOrder: String?=""
)