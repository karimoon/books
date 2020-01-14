package com.karim.booksapp.ui.main


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.karim.booksapp.data.repository.BookRepository
import javax.inject.Inject

class MainViewModel
@Inject
constructor(val app : Application, val bookRepository: BookRepository) : AndroidViewModel(app)

{

    val bookData = bookRepository.bookData

    val favoriteBooksData = bookRepository.favoriteBooksData

    val queryMapSelected = bookRepository.queryMapSelected

    val isNetworkAvailable = bookRepository.isNetworkAvailable


    fun refreshData(mSortBy: String) {
        bookRepository.refreshDataFromWeb(mSortBy)
    }

    fun searchBooksWithQueryWithPagination(queryMap : HashMap<String,String>){
        bookRepository.searchBooksWithQueryFromWebWithPagination(queryMap)
    }

    fun getFavoriteBooks(){
        bookRepository.getFavoriteBooks()
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
