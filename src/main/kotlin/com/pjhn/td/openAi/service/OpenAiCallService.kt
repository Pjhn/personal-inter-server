package com.pjhn.td.openAi.service

import com.pjhn.td.openAi.dto.response.OpenAiResponse
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import java.util.Base64

@Service
class OpenAiCallService(
    @Value("\${openai.model}") private val apiModel: String,
    @Value("\${openai.api.url}") private val apiUrl: String,
    private val template: RestTemplate
) {
    fun requestImageAnalysis(image: ByteArray, requestText: String): OpenAiResponse? {
        val base64Image = Base64.getEncoder().encodeToString(image)
        val imageURL = "data:image/jpeg;base64,$base64Image"

        val payload = buildJsonObject {
            put("model", apiModel)
            putJsonArray("messages") {
                add(buildJsonObject {
                    put("role", "user")
                    putJsonArray("content"){
                        add(buildJsonObject {
                            put("type", "text")
                            put("text", requestText)
                        })
                        add(buildJsonObject {
                            put("type", "image_url")
                            putJsonObject("image_url"){put("url", imageURL)}
                        })
                    }
                })
            }
        }

        return template.postForObject(apiUrl, payload, OpenAiResponse::class.java)
    }
}