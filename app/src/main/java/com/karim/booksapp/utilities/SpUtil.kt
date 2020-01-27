package com.karim.booksapp.utilities

import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE



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