package com.mv.chatappmobile.components.ui.chat.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity
import com.mv.chatappmobile.sqlite.entities.chats.ChatMessageEntity
import com.mv.chatappmobile.sqlite.repositories.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(): ViewModel() {


    private val _chatMessages: MutableStateFlow<List<ChatMessageEntity>> = MutableStateFlow(emptyList())
    val chatMessages: StateFlow<List<ChatMessageEntity>> get() = _chatMessages

    fun getMessagesByChatId(chatId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
//            _chatMessages.value = chatRepository.getMessagesByChatId(chatId)
        }
    }

    fun insertChatMessage(chatMessage: ChatMessageEntity) {
        viewModelScope.launch(Dispatchers.IO) {
//            chatRepository.insertChatMessage(chatMessage)
        }
    }

    init {
        // Create and connect WebSocketClient here

    }


    override fun onCleared() {
        // disconnect the ws
        super.onCleared()
    }
}