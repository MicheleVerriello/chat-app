package com.mv.chatappmobile.components.ui.chat

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mv.chatappmobile.data.ChatPreview

@Composable
fun ChatPreviewItemScreen(chatPreview: ChatPreview) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray
            )
            .padding(16.dp)
    ) {
        Text(text = "Receiver: ${chatPreview.receiverUsername}")
        Text(text = "ID: ${chatPreview.id}")
        Text(text = "Last Message: ${chatPreview.lastMessage.message}")
        Text(text = "Date: ${chatPreview.lastMessage.date}")
    }
}