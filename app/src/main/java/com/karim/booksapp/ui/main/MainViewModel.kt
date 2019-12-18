package com.karim.booksapp.ui.main


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.karim.booksapp.data.repository.BookRepository


class MainViewModel(app: Application) : AndroidViewModel(app) {

    val dataRepo = BookRepository(app)

    val bookData = dataRepo.bookData

    val favoriteBooksData = dataRepo.favoriteBooksData

    val queryMapSelected = dataRepo.queryMapSelected

    val isNetworkAvailable = dataRepo.isNetworkAvailable

    fun refreshData(mSortBy: String) {
        dataRepo.refreshDataFromWeb(mSortBy)
    }

    fun searchBooksWithQueryWithPagination(queryMap : HashMap<String,String>){
        dataRepo.searchBooksWithQueryFromWebWithPagination(queryMap)
    }

    fun getFavoriteBooks(){
        dataRepo.getFavoriteBooks()
    }

    fun searchBooksWithQuery(queryMap : HashMap<String,String>){
        dataRepo.searchBooksWithQueryFromWeb(queryMap)
    }

    fun displayBooksFromCache(){
        dataRepo.displayBooksFromCache()
    }

    fun configSearchWithDefaultParams(query : String): HashMap<String, String> {

        return dataRepo.configSearchWithDefaultParams(query)
    }

    fun isNetworkAvailable(): Boolean {
        return dataRepo.networkAvalable()
    }


}
