package com.mv.chatappmobile.components.ui.chat.list

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mv.chatappmobile.network.response.User
import com.mv.chatappmobile.network.services.RetrofitInstance
import com.mv.chatappmobile.network.services.UserService
import com.mv.chatappmobile.sqlite.configuration.AppDatabase
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatListViewModel: ViewModel() {

    private val userService = RetrofitInstance.retrofit.create(UserService::class.java)
    private val _users: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val users: StateFlow<List<User>> get() = _users

    private val _chats: MutableStateFlow<List<ChatEntity>> = MutableStateFlow(emptyList())
    val chats: StateFlow<List<ChatEntity>> get() = _chats


    suspend fun updateUsers(username: String) {
        viewModelScope.launch {
            val response = userService.getUsersByUsername(username = username)
            _users.value = response.body()?.users!!
            Log.d("updateUsers", _users.value.toString())
        }

    }

    fun clearUsers() {
        _users.value = emptyList()
    }

    fun getAllChats(context: Context) {
        val chatDao = AppDatabase.getDatabase(context).chatDao()
        _chats.value = emptyList()
        viewModelScope.launch(Dispatchers.IO) {
            _chats.value = chatDao.getAllChats()
        }
    }

    fun addChat(context: Context, chatEntity: ChatEntity) {
        val chatDao = AppDatabase.getDatabase(context).chatDao()
        viewModelScope.launch(Dispatchers.IO) {
            if(!chatDao.doesChatExist(chatEntity.usernameReceiver)) {
                chatDao.insertChat(chatEntity)

                // Switch to the main dispatcher
                withContext(Dispatchers.Main) {
                    getAllChats(context)
                }
            }
        }
    }
}