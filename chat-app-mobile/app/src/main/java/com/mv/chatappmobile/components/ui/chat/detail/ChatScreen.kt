package com.mv.chatappmobile.components.ui.chat.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mv.chatappmobile.data.MinifiedChatMessage
import com.mv.chatappmobile.network.websocket.ChatWebSocketClient
import com.mv.chatappmobile.network.websocket.ChatWebSocketListener
import com.mv.chatappmobile.utilities.sharedpreferences.SharedPreferencesManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(receiverUsername: String?) {

    val context = LocalContext.current
    val ownerUsername: String? = SharedPreferencesManager.get(context, "username")
    val listener = ChatWebSocketListener()
    val webSocketClient = ChatWebSocketClient(listener, ownerUsername)
    webSocketClient.connectWebSocket()

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
            Text(
                text = "Message 1\nMessage 2\nMessage 3", // Replace with actual messages
                modifier = Modifier.padding(16.dp)
            )
        }

        // Bottom Text Field with Send Icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = { /* Handle text input here */ },
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
                    onSend = { /* Handle send action here */ }
                ),
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                placeholder = {Text(text = "Write your message here")}
            )

            IconButton(
                onClick = {
                    webSocketClient.sendChatMessage(MinifiedChatMessage("micheleverriello", "gianperotulipani", "test"))
                    webSocketClient.disconnectWebSocket()
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