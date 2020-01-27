package com.karim.booksapp.data.repository

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.karim.booksapp.*
import com.karim.booksapp.data.database.BookDao
import com.karim.booksapp.data.models.Book
import com.karim.booksapp.data.database.BookDatabase
import com.karim.booksapp.data.retrofit.BookRetrofitService
import com.karim.booksapp.data.models.pojos.BooksEntry
import com.karim.booksapp.data.utils.ParserUtil
import com.karim.booksapp.utilities.FileHelper
import com.karim.booksapp.utilities.SpUtil
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class BookDbRepository
@Inject
constructor(val bookDao : BookDao) {

    val favoriteBooksData = MutableLiveData<List<Book>>()

    @WorkerThread
    suspend fun getFavoriteBooksFromDatabase(){
        var books = bookDao.getAll()

        books?.let {
            favoriteBooksData.postValue(books)
        }
    }

    fun getFavoriteBooks(): MutableLiveData<List<Book>>? {

        CoroutineScope(Dispatchers.IO).launch {
            getFavoriteBooksFromDatabase()
        }

        return null
    }

    fun findBookByIdTest(id : String): Book{
        return Book()
    }

    suspend fun insertEntry(book: Book){
        return bookDao.insertBook(book)
    }

    suspend fun deleteEntry(book: Book){
        return bookDao.delete(book)
    }

    suspend fun findBookById(id : String): Book {
        return bookDao.findBookById(id)
    }


}