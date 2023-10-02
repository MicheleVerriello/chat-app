package com.mv.chatappmobile.sqlite.repositories

import com.mv.chatappmobile.sqlite.dao.ChatDao
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity
import com.mv.chatappmobile.sqlite.entities.chats.ChatMessageEntity

class ChatRepository(private val chatDao: ChatDao) {

    suspend fun insertChat(chat: ChatEntity) {
        chatDao.insertChat(chat)
    }

    suspend fun insertChatMessage(chatMessage: ChatMessageEntity) {
        chatDao.insertChatMessage(chatMessage)
    }

    suspend fun getAllChats(): List<ChatEntity> {
        return chatDao.getAllChats()
    }

    suspend fun getMessagesByChatId(chatId: Long): List<ChatMessageEntity> {
        return chatDao.getMessagesByChatId(chatId)
    }
}