package com.mv.chatappmobile.components.navigation

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")
    object ScaffoldScreen: Screen("scaffold_screen")
    object ChatScreen: Screen("chat_screen")
    object ChatListScreen: Screen("chat_list_screen")
    object SettingsScreen: Screen("settings_screen")
    object ProfileScreen: Screen("profile_screen")
    object MediaScreen: Screen("media_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
