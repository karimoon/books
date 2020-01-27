package com.karim.booksapp.di.ui.main

import com.karim.booksapp.ui.main.BookListFragment
import com.karim.booksapp.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeBookListFragment(): BookListFragment

    @ContributesAndroidInjector()
    abstract fun contributeSearchFragment(): SearchFragment

}