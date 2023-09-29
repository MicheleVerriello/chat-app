package com.mv.chatappmobile.network.services

import com.mv.chatappmobile.network.request.LoginRequestBody
import com.mv.chatappmobile.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizeService {
    @POST("${HttpRoutes.BASE_URL}authorize/login")
    suspend fun login(@Body requestBody: LoginRequestBody): Response<LoginResponse>
}