@file:OptIn(ExperimentalMaterial3Api::class)

package com.mv.chatappmobile.components.ui.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mv.chatappmobile.components.navigation.Screen
import com.mv.chatappmobile.network.request.LoginRequestBody
import com.mv.chatappmobile.utilities.sharedpreferences.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: LoginViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            OutlinedTextField( modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it },
                placeholder = { Text(text = "Enter username") },
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField( modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "Enter password") },
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        val response = viewModel.login(loginRequestBody = LoginRequestBody(username, password))
                        if (response.isSuccessful) {
                            Log.d("LOGIN RESPONSE OK", response.body().toString())
                            CoroutineScope(Dispatchers.Main).launch {
                                SharedPreferencesManager.put(context = context, "id", response.body()!!.id)
                                navController.navigate(Screen.ScaffoldScreen.route)
                            }

                        } else {
                            Log.d("LOGIN RESPONSE ERROR","${response.code()} - ${response.message()}")
                        }
                    }
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Row {
                Text(text = "Not Registered ? ")
                Text(
                    text = "Sign Up",
                    modifier = Modifier.clickable(
                        onClick = {
                            navController.navigate(Screen.SignUpScreen.route)
                        },
                        enabled = true
                    )
                )
            }
        }
    }
}