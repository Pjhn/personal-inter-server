package com.pjhn.td.openAi.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.NotNull

@Serializable
data class OpenAiRequest(
    @NotNull val model: String,
    @NotNull val messages: List<Message>,
    @SerialName("max_tokens") val maxTokens: Int
)