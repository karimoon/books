package com.karim.booksapp.utils

import android.content.SharedPreferences

class SpUtil {
    companion object{

        val PREF_NAME = "BooksPreferences"

        fun getPreferenceString(sharedPreferences : SharedPreferences, key: String): String? {
            return sharedPreferences.getString(key, "")
        }

        fun setPreferenceString(sharedPrefsEditor : SharedPreferences.Editor, key: String, value: String) {
            sharedPrefsEditor.putString(key, value)
            sharedPrefsEditor.apply()
        }

    }
}