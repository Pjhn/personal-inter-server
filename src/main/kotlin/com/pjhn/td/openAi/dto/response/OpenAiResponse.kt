package com.pjhn.td.openAi.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class OpenAiResponse(
    val choices: List<Choice>
)
