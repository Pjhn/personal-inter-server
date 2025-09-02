package com.pjhn.td.openAi.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val index: Int,
    val message: ResponseMessage,
    val logprobs: String? = null,
    @SerialName("finish_reason") val finishReason: String? = null
)
