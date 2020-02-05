package com.karim.booksapp.screens.main.helpers

import com.karim.booksapp.models.Book

interface FileHelperWrapper {

    fun saveDataToCache(monsterData: List<Book>)

    fun readDataFromCache(): List<Book>
}