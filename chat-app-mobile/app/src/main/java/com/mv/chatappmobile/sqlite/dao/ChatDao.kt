package com.mv.chatappmobile.sqlite.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity
import com.mv.chatappmobile.sqlite.entities.chats.ChatMessageEntity

@Dao
interface ChatDao {
    @Insert
    suspend fun insertChat(chat: ChatEntity)

    @Insert
    suspend fun insertChatMessage(chatMessage: ChatMessageEntity)

    @Query("SELECT * FROM chats")
    suspend fun getAllChats(): List<ChatEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM chats WHERE usernameReceiver = :usernameReceiver LIMIT 1)")
    suspend fun doesChatExist(usernameReceiver: String): Boolean

    @Query("SELECT * FROM chatmessages WHERE fkIdChat = :chatId")
    suspend fun getMessagesByChatId(chatId: Long): List<ChatMessageEntity>
}