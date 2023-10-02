package com.mv.chatappmobile.components.ui.chat.preview

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity

@Composable
fun ChatPreviewItemListScreen(
    chats: List<ChatEntity>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .fillMaxHeight(0.5f)
    ) {
        items(
            items = chats,
            itemContent = {
                ChatPreviewScreen(chat = it, navController = navController)
            }

        )
    }
}