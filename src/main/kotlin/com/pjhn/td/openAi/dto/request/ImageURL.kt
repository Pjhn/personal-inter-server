package com.pjhn.td.openAi.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class ImageURL (
    private val url: String
)