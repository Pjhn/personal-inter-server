package com.pjhn.td.openAi.config

import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class OpenAiConfig (
    @Value("\${openai.api.key}") private val apiKey: String,
){
    @Bean
    fun template(): RestTemplate {
        val kotlinJson = Json {
            ignoreUnknownKeys = true
        }
        val restTemplate = RestTemplate()
        val jsonConverter = KotlinSerializationJsonHttpMessageConverter(kotlinJson)
        val interceptor = ClientHttpRequestInterceptor { request, body, execution ->
            request.headers.setBearerAuth(apiKey)
            request.headers.contentType = MediaType.APPLICATION_JSON
            execution.execute(request,body)
        }

        restTemplate.messageConverters.add(0,jsonConverter)
        restTemplate.interceptors = listOf(interceptor)
        return restTemplate
    }
}
