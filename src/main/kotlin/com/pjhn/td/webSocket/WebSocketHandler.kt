package com.pjhn.td.webSocket

import com.pjhn.td.openAi.service.OpenAiCallService
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

@Component
class WebSocketHandler(
    private val openAiCallService: OpenAiCallService
): TextWebSocketHandler() {

    companion object {
        private val CLIENTS: MutableMap<String, WebSocketSession> = ConcurrentHashMap()
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        CLIENTS[session.id] = session
        super.afterConnectionEstablished(session)
    }

    override fun afterConnectionClosed(
        session: WebSocketSession,
        status: CloseStatus
    ) {
        CLIENTS.remove(session.id)
        super.afterConnectionClosed(session, status)
    }

    override fun handleTextMessage(
        session: WebSocketSession,
        message: TextMessage
    ) {
        val id = session.id
        CLIENTS.entries.forEach { client ->
            // 클라이언트들 중에서 요청을 보내는 클라이언트와 같으면 메세지 전송
            // 여러 클라이언트들이 접속하고 메세지를 확인하려면 client.key != id 로 바꾸면 된다
            if(client.key == id){
                try {
                    val image = ClassPathResource("mono.png")
                    val imageBytes = image.contentAsByteArray
                    val response = openAiCallService.requestImageAnalysis(imageBytes,"Describe this black-and-white image in one short sentence")
                    val responseMessage: String = response?.choices?.get(0)?.message?.content ?: "empty"

                    session.sendMessage(TextMessage(responseMessage))
                } catch (e: IOException){
                    e.printStackTrace()
                }
            }
        }
        super.handleTextMessage(session, message)
    }
}