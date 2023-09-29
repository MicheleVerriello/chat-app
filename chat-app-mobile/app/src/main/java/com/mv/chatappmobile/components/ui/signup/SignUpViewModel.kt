package com.mv.chatappmobile.components.ui.signup

import androidx.lifecycle.ViewModel
import com.mv.chatappmobile.network.request.SignUpRequestBody
import com.mv.chatappmobile.network.response.LoginResponse
import com.mv.chatappmobile.network.services.RetrofitInstance
import com.mv.chatappmobile.network.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SignUpViewModel: ViewModel() {

    private val userService = RetrofitInstance.retrofit.create(UserService::class.java)

    suspend fun signUp(signUpRequestBody: SignUpRequestBody): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            userService.signUp(signUpRequestBody)
        }
    }
}


