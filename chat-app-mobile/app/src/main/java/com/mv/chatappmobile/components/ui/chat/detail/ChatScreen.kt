package com.mv.chatappmobile.components.ui.chat.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fasterxml.jackson.databind.ObjectMapper
import com.mv.chatappmobile.data.MinifiedChatMessage
import com.mv.chatappmobile.network.websocket.ChatWebSocketClient
import com.mv.chatappmobile.network.websocket.ChatWebSocketListener
import com.mv.chatappmobile.utilities.sharedpreferences.SharedPreferencesManager
import okhttp3.WebSocket


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(receiverUsername: String?) {

    val context = LocalContext.current
    val ownerUsername: String? = SharedPreferencesManager.get(context, "username")
    var webSocketClient = ChatWebSocketClient(ChatWebSocketListener(), ownerUsername)
    val chatMessages = remember { mutableStateListOf<MinifiedChatMessage>() }
    var messageText by remember { mutableStateOf("") }

    // WebSocket message listener
    DisposableEffect(Unit) {
        val messageListener = object : ChatWebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                val mapper = ObjectMapper()

                val receivedMessage = mapper.readValue(text, MinifiedChatMessage::class.java) // Implement this function to parse the received message
                Log.d("Disposable WS", "onMessage: ${receivedMessage.message}")
                chatMessages.add(receivedMessage)
            }
        }

        // Attach the listener to your WebSocketClient
        webSocketClient = ChatWebSocketClient(messageListener, ownerUsername)
        webSocketClient.connectWebSocket()

        onDispose {
            // Remove the listener when this Composable is disposed
            Log.d("Disposable WS", "onDispose")
            webSocketClient.disconnectWebSocket()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(
                text = "@$receiverUsername",
                style = TextStyle(fontWeight = FontWeight.Bold)
            ) },
        )

        // Central Part (Messages)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // Add your message display logic or composable here
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                reverseLayout = true // Reverse the order to display the latest messages at the bottom
            ) {
                items(chatMessages) { message ->
                    Text(
                        text = "${message.usernameSender}: ${message.message}",
                        modifier = Modifier.padding(8.dp),
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }

        }

        // Bottom Text Field with Send Icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField (
                value = messageText,
                onValueChange = { messageText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .border(color = Color.Gray, width = 1.dp, shape = RoundedCornerShape(10.dp))
                    .height(64.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        val newMessage = messageText
                        if (ownerUsername != null && receiverUsername != null) {

                            val sentMessage = MinifiedChatMessage(ownerUsername, receiverUsername, newMessage)
                            webSocketClient.sendChatMessage(sentMessage)
                            chatMessages.add(sentMessage)
                            messageText = ""
                        }
                    }
                ),
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            )

            IconButton(
                onClick = {

                    val newMessage = messageText
                    if (ownerUsername != null && receiverUsername != null) {

                        val sentMessage = MinifiedChatMessage(ownerUsername, receiverUsername, newMessage)
                        webSocketClient.sendChatMessage(sentMessage)
                        chatMessages.add(sentMessage)
                        messageText = ""
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send"
                )
            }
        }
    }
}

//try with a private function