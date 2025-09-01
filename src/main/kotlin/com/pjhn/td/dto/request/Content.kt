package com.pjhn.td.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val type: String,
    val text: String? = null,
    @SerialName("image_url") val imageURL: ImageURL? = null
)