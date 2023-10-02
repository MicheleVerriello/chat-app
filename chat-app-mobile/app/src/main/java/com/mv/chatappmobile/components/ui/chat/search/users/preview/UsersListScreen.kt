package com.mv.chatappmobile.components.ui.chat.search.users.preview

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mv.chatappmobile.components.ui.chat.list.ChatListViewModel
import com.mv.chatappmobile.network.response.User
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity

@Composable
fun UsersListScreen(
    users: List<User>,
    viewModel: ChatListViewModel,
    context: Context
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(128.dp)
    ) {
        items(
            items = users,
            itemContent = {
                UserPreviewScreen(user = it) {
                    viewModel.clearUsers()
                    viewModel.addChat(context = context, ChatEntity(usernameReceiver = it.username))
                }
            }
        )
    }
}