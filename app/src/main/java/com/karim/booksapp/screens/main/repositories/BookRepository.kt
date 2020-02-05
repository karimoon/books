package com.karim.booksapp.screens.main.repositories

import com.karim.booksapp.models.Book

interface BookRepository {

    suspend fun searchBooksFromWeb(query: HashMap<String,String>): List<Book>

    suspend fun searchBooksFromWebWithPagination(query: HashMap<String,String>): List<Book>

    fun configSearchWithDefaultParams(query : String): HashMap<String, String>

    fun configSearchWithDefaultParams(
        title: String,
        author: String,
        publisher: String,
        isbn: String
    ): HashMap<String, String>
}