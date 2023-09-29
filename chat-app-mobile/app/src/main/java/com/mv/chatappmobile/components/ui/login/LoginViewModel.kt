package com.mv.chatappmobile.components.ui.login

import androidx.lifecycle.ViewModel
import com.mv.chatappmobile.network.request.LoginRequestBody
import com.mv.chatappmobile.network.response.LoginResponse
import com.mv.chatappmobile.network.services.AuthorizeService
import com.mv.chatappmobile.network.services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginViewModel: ViewModel() {

    private val authorizeService = RetrofitInstance.retrofit.create(AuthorizeService::class.java)

    suspend fun login(loginRequestBody: LoginRequestBody): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            authorizeService.login(loginRequestBody)
        }
    }
}


