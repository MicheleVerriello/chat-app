package com.mv.chatappmobile.components.ui.chat.preview

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mv.chatappmobile.components.navigation.Screen
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity

@Composable
fun ChatPreviewScreen(
    chat: ChatEntity,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray
            )
            .height(80.dp)
            .clickable { navController.navigate(Screen.ChatScreen.withArgs(chat.usernameReceiver)) },
    ) {
        Text(text = "@${chat.usernameReceiver}")
    }

}