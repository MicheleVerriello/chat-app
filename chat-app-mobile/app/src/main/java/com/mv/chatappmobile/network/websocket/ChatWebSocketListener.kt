package com.mv.chatappmobile.network.websocket

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

open class ChatWebSocketListener : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        // Handle WebSocket connection opened
        Log.i("socket onOpen", "connection opened")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        try {
            Log.d("socket onMessage", text)
        } catch (e: Exception) {
            // Handle JSON parsing error
            Log.e("socket onMessage", e.message.toString())
        }
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        // Handle WebSocket connection failure
        Log.e("socket onFailure", t.message.toString())
        Log.e("socket onFailure", t.stackTrace.toString())
        Log.e("socket onFailure", t.cause!!.message!!)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        // Handle WebSocket connection closed
        Log.i("socket onClosed", code.toString())
    }
}