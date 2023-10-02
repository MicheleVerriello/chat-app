package com.mv.chatappmobile.components.ui.chat.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mv.chatappmobile.components.ui.chat.preview.ChatPreviewItemListScreen
import com.mv.chatappmobile.components.ui.chat.search.users.preview.UsersListScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController) {

    var searchText by remember { mutableStateOf("") }
    val viewModel: ChatListViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Observe the LiveData directly in the Composable
    val users by viewModel.users.collectAsState(initial = emptyList())
    val chats by viewModel.chats.collectAsState(initial = emptyList())

    viewModel.getAllChats(context)



    Column {
        TextField(
            value = searchText,
            onValueChange = { username ->
                searchText = username

                if (username.isNotEmpty() && username.isNotBlank()) {
                    coroutineScope.launch(Dispatchers.IO) {
                        viewModel.updateUsers(username)
                    }
                }
                else {
                    viewModel.clearUsers()
                }
            },
            placeholder = { Text("Search Users") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Display the chat list
        ChatPreviewItemListScreen(chats = chats, navController = navController)

        // Display the user list based on the search results
        UsersListScreen(users = users, viewModel = viewModel, context = context)
    }
}