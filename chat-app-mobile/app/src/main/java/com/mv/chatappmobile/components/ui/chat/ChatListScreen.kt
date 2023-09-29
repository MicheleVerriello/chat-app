package com.mv.chatappmobile.components.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen() {

    var searchText by remember { mutableStateOf("") }
    val viewModel: ChatListViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    // Observe the LiveData directly in the Composable
    val users by viewModel.users.collectAsState(initial = emptyList())


    Column {
        TextField(
            value = searchText,
            onValueChange = { username ->
                searchText = username

                if (username.isNotEmpty() && username.isNotBlank()) {
                    coroutineScope.launch(Dispatchers.IO) {
                        // Call the get function on search query change
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

        // Display the user list based on the search results
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(items = users, itemContent = { user ->
                // Add borders to each item
                Text(
                    text = "@${user.username} | ${user.name} ${user.surname}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray
                        )
                        .background(
                            color = Color.Gray
                        )
                        .height(24.dp)
                )
            })
        }

    }
}