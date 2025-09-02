package com.pjhn.td.openAi.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMessage(
    val role: String,
    val content: String,
    val refusal: String? = null,
    val annotations: List<String>? = null
)
