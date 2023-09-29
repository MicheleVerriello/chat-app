package com.mv.chatappmobile.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mv.chatappmobile.components.ui.chat.ChatListScreen
import com.mv.chatappmobile.components.ui.chat.ChatScreen
import com.mv.chatappmobile.components.ui.login.LoginScreen
import com.mv.chatappmobile.components.ui.signup.SignUpScreen
import com.mv.chatappmobile.components.ui.scaffold.ScaffoldScreen
import com.mv.chatappmobile.components.ui.settings.MediaScreen
import com.mv.chatappmobile.components.ui.settings.ProfileScreen
import com.mv.chatappmobile.components.ui.settings.SettingsScreen

@Composable
fun Navigation(
    startDestination: String,
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
        composable(route = Screen.MediaScreen.route) {
            MediaScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.ChatScreen.route) {
            ChatScreen()
        }
        composable(route = Screen.ChatListScreen.route) {
            ChatListScreen()
        }
    }
}

@Composable
fun Navigation(
    startDestination: String,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.ScaffoldScreen.route) {
            ScaffoldScreen()
        }
    }
}