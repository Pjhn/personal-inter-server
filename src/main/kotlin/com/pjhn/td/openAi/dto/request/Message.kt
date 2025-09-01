package com.pjhn.td.openAi.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val role: String,
    val content: List<Content>
)
