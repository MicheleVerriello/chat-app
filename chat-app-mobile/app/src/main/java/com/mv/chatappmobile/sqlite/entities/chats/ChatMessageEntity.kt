package com.mv.chatappmobile.sqlite.entities.chats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "chatmessages",
    foreignKeys = [
        ForeignKey(
            entity = ChatEntity::class,
            parentColumns = ["id"],
            childColumns = ["fkIdChat"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "fkIdChat")
    val fkIdChat: Long, // Foreign key reference to the "chats" table

    @ColumnInfo(name = "usernameReceiver")
    val usernameReceiver: String,

    @ColumnInfo(name = "usernameSender")
    val usernameSender: String,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "sentDate")
    val sentDate: Long
)