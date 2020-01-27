package com.karim.booksapp.ui.main


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.karim.booksapp.LOG_TAG
import com.karim.booksapp.data.repository.BookDbRepository
import com.karim.booksapp.data.repository.BookRepository
import javax.inject.Inject

class MainViewModel
@Inject
constructor(val bookRepository: BookRepository , val bookDbRepository: BookDbRepository) : ViewModel()

{

    val bookData = bookRepository.bookData

    val favoriteBooksData = bookDbRepository.favoriteBooksData

    val queryMapSelected = bookRepository.queryMapSelected

    val isNetworkAvailable = bookRepository.isNetworkAvailable


    fun refreshData(mSortBy: String) {
        bookRepository.refreshDataFromWeb(mSortBy)
    }

    fun searchBooksWithQueryWithPagination(queryMap : HashMap<String,String>){
        bookRepository.searchBooksWithQueryFromWebWithPagination(queryMap)
    }

    fun getFavoriteBooks(){
        bookDbRepository.getFavoriteBooks()
    }

    fun searchBooksWithQuery(queryMap : HashMap<String,String>){
        bookRepository.searchBooksWithQueryFromWeb(queryMap)
    }

    fun displayBooksFromCache(){
        bookRepository.displayBooksFromCache()
    }

    fun configSearchWithDefaultParams(query : String): HashMap<String, String> {

        return bookRepository.configSearchWithDefaultParams(query)
    }

    fun isNetworkAvailable(): Boolean {
        return bookRepository.networkAvalable()
    }

}
