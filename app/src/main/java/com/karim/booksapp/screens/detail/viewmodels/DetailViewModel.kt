package com.karim.booksapp.screens.detail.viewmodels

import androidx.lifecycle.ViewModel
import com.karim.booksapp.models.Book
import com.karim.booksapp.screens.main.repositories.BookDbRepository
import javax.inject.Inject

class DetailViewModel
@Inject
constructor(val bookDbRepository: BookDbRepository) : ViewModel(){

    suspend fun removeFromFavorites(book : Book) {

        val isFavorite = isFavorite(book)

        if(isFavorite){
            bookDbRepository.deleteEntry(book)
        }
    }

    suspend fun markAsFavorite(book : Book) {

        val isFavorite = isFavorite(book)
        if(!isFavorite){
            bookDbRepository.insertEntry(book)
        }
    }

    suspend fun isFavorite(book : Book): Boolean {

        var  bookretrieved = book?.id?.let {
            bookDbRepository.findBookById(it)
        }
        return bookretrieved != null
    }
}