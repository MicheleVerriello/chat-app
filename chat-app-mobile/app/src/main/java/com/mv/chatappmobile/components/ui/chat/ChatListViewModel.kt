package com.mv.chatappmobile.components.ui.chat

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mv.chatappmobile.network.response.User
import com.mv.chatappmobile.network.services.RetrofitInstance
import com.mv.chatappmobile.network.services.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatListViewModel: ViewModel() {

    private val userService = RetrofitInstance.retrofit.create(UserService::class.java)
    private val _users: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val users: StateFlow<List<User>> get() = _users


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
}