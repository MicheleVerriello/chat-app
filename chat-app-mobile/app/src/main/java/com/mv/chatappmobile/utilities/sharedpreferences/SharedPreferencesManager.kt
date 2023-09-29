package com.mv.chatappmobile.utilities.sharedpreferences

import android.content.Context


class SharedPreferencesManager {

    companion object {

        private const val preferencesName = "chatappmobileprefs"

        fun put(context: Context, key: String, value: String) {
            val prefs = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun get(context: Context, key: String): String? {
            val prefs = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
            return prefs.getString(key, null)
        }
    }
}