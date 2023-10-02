package com.mv.chatappmobile.sqlite.configuration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mv.chatappmobile.sqlite.dao.ChatDao
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity
import com.mv.chatappmobile.sqlite.entities.chats.ChatMessageEntity

@Database(entities = [ChatEntity::class, ChatMessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "chat_app_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}