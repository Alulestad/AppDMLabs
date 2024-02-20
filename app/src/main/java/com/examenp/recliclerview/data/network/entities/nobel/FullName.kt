package com.examenp.recliclerview.data.network.entities.nobel

import com.examenp.recliclerview.data.network.entities.nobelserializable.FullName
import kotlinx.serialization.Serializable

@Serializable
data class FullName(
    val en: String?
)