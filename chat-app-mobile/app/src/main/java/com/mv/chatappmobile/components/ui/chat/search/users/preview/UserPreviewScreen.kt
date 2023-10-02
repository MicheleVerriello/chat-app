package com.mv.chatappmobile.components.ui.chat.search.users.preview

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
import com.mv.chatappmobile.network.response.User

@Composable
fun UserPreviewScreen(
    user: User,
    onClick:()->Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray
            )
            .height(32.dp)
            .clickable { onClick() },
    ) {
        Text(text = "@${user.username} | ${user.name} ${user.surname}")
    }
}