package com.karim.booksapp.di.screens.main

import com.karim.booksapp.screens.main.fragments.BookListFragment
import com.karim.booksapp.ui.search.AdvancedSearchFragment
import com.karim.booksapp.screens.main.fragments.SearchFragment
import com.karim.booksapp.screens.main.fragments.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeBookListFragment(): BookListFragment

    @ContributesAndroidInjector()
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector()
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector()
    abstract fun contributeAdvancedSearchFragment(): AdvancedSearchFragment
}