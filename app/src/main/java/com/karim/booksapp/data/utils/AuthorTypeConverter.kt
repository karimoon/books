package com.karim.booksapp.data.utils

import androidx.room.TypeConverter

class AuthorTypeConverter {

    @TypeConverter
    fun listToString(someObjects: Array<String?>): String {
        return someObjects.joinToString(", ")
    }
}