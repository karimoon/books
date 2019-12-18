package com.karim.booksapp.utilities

import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE



class SpUtil {
    companion object{

        val PREF_NAME = "BooksPreferences"

        private fun getPrefs(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }

        fun getPreferenceString(context: Context, key: String): String? {
            return getPrefs(context).getString(key, "")
        }

        fun getPreferenceInt(context: Context, key: String): Int {
            return getPrefs(context).getInt(key, 0)
        }

        fun setPreferenceString(context: Context, key: String, value: String) {
            val editor = getPrefs(context).edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun setPreferenceInt(context: Context, key: String, value: Int) {
            val editor = getPrefs(context).edit()
            editor.putInt(key, value)
            editor.apply()
        }

    }
}