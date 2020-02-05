package com.karim.booksapp.screens.main.helpers

import android.app.Application
import com.karim.booksapp.models.Book
import com.karim.booksapp.repositories.PermissionRepository
import com.karim.booksapp.screens.main.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

class FileHelperWrapperImpl
@Inject
constructor(val app :Application , val permissionRepository : PermissionRepository) :
    FileHelperWrapper
{

    override fun saveDataToCache(monsterData: List<Book>) {
        try {
            if (permissionRepository.isWriteExternalStoragePermissionGranted()) {
                val moshi = Moshi.Builder().build()
                val listType = Types.newParameterizedType(List::class.java, Book::class.java)
                val adapter: JsonAdapter<List<Book>> = moshi.adapter(listType)
                val json = adapter.toJson(monsterData)
                FileHelper.saveTextToFile(app, json)
            }
        } catch (e: ConcurrentModificationException) {
            e.printStackTrace()
        }
    }

    override fun readDataFromCache(): List<Book> {
        val json = FileHelper.readTextFile(app)
        if (json == null) {
            return emptyList()
        }
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Book::class.java)
        val adapter: JsonAdapter<List<Book>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }
}