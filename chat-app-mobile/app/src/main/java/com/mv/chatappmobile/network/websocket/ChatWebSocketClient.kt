package com.mv.chatappmobile.network.websocket

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.mv.chatappmobile.data.MinifiedChatMessage
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class ChatWebSocketClient(
    private val listener: WebSocketListener,
    private val username: String?
) {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    fun connectWebSocket() {
        val request = Request.Builder()
            .url("ws://192.168.1.5:8080/chat")
            .addHeader("username", username)
            .build()

        webSocket = client.newWebSocket(request, listener)
    }

    private fun sendMessage(message: String) {
        Log.d("sendMessage", message)
        try {
            webSocket?.send(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun disconnectWebSocket() {
        webSocket?.close(1000, "User initiated disconnect")
    }

    fun sendChatMessage(chatMessage: MinifiedChatMessage) {
        Log.d("sendChatMessage", chatMessage.toString())
        val objectMapper = ObjectMapper()
        sendMessage(objectMapper.writeValueAsString(chatMessage))
    }
}