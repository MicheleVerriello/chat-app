package com.mv.chatappmobile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mv.chatappmobile.components.navigation.Navigation
import com.mv.chatappmobile.components.navigation.Screen
import com.mv.chatappmobile.ui.theme.ChatappmobileTheme
import com.mv.chatappmobile.utilities.sharedpreferences.SharedPreferencesManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId: String? = SharedPreferencesManager.get(applicationContext, "id")

        setContent {
            ChatappmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (userId != null) {
                        Navigation(Screen.ScaffoldScreen.route)
                    } else {
                        Navigation(Screen.LoginScreen.route)
                    }
                }
            }
        }
    }
}