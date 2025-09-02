package com.pjhn.td.openAi.controller

import com.pjhn.td.openAi.service.OpenAiCallService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api")
class OpenAiController(
    private val openAiService: OpenAiCallService
) {

    @PostMapping("/image")
    fun imageAnalysis(@RequestParam image: MultipartFile, @RequestParam requestText: String): String{
        val response = openAiService.requestImageAnalysis(image,requestText)
        return response?.choices?.get(0)?.message?.content ?: "empty"
    }
}