package com.examenp.recliclerview.data.network.entities.nobel

data class Laureate(
    val fullName: FullName?,
    val id: String,
    val knownName: KnownName,
    val links: List<LinkX>,
    val motivation: Motivation,
    val nativeName: String,
    val orgName: OrgName,
    val portion: String,
    val sortOrder: String
)