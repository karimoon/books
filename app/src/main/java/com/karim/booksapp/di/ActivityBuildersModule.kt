package com.karim.booksapp.di


import com.karim.booksapp.di.ui.main.MainModule
import com.karim.booksapp.di.ui.detail.DetailModule
import com.karim.booksapp.di.ui.main.MainFragmentBuildersModule
import com.karim.booksapp.di.ui.main.MainScope
import com.karim.booksapp.di.ui.main.MainViewModelsModule
import com.karim.booksapp.ui.MainActivity
import com.karim.booksapp.ui.detail.DetailBookActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainViewModelsModule::class , MainFragmentBuildersModule::class , MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun contributeDetailBookActivity(): DetailBookActivity


}
