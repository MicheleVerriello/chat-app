package com.mv.chatappmobile.network.request

data class SignUpRequestBody(
    val name: String,
    val surname: String,
    val username: String,
    val password: String
)
