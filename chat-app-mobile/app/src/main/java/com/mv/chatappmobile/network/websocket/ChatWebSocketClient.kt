package com.mv.chatappmobile.network.websocket

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocketListener


class ChatWebSocketClient(val webSocketListener: WebSocketListener) {

    private val client = OkHttpClient()

    fun connectWebSocket() {
        val request = Request.Builder().url("YOUR_WEBSOCKET_SERVER_URL").build()
        val webSocket = client.newWebSocket(request, webSocketListener)
    }

    fun disconnectWebSocket() {
        client.dispatcher().executorService().shutdown()
    }
}