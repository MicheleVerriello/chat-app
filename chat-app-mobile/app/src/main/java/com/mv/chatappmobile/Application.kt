package com.mv.chatappmobile

import android.app.Application
import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.mv.chatappmobile.sqlite.configuration.AppDatabase
import com.mv.chatappmobile.sqlite.entities.chats.ChatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}