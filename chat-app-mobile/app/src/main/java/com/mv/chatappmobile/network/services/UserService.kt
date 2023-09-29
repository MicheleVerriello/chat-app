package com.mv.chatappmobile.network.services

import com.mv.chatappmobile.network.response.LoginResponse
import com.mv.chatappmobile.network.request.SignUpRequestBody
import com.mv.chatappmobile.network.response.UserListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST(HttpRoutes.USERS)
    suspend fun signUp(@Body requestBody: SignUpRequestBody): Response<LoginResponse>
    @GET(HttpRoutes.USERS)
    suspend fun getUsersByUsername(@Query("username") username: String): Response<UserListResponse>
}