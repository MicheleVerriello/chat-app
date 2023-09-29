package com.mv.chatappmobile.data

import java.sql.Timestamp

data class ChatMessage(
    val idSender: String,
    val idReceiver: String,
    val message: String,
    val date: Timestamp,
    val idChat: String
)
