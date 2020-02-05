package com.karim.booksapp.di


import com.karim.booksapp.di.screens.main.MainModule
import com.karim.booksapp.di.screens.detail.DetailModule
import com.karim.booksapp.di.screens.detail.DetailViewModelsModule
import com.karim.booksapp.di.screens.main.MainFragmentBuildersModule
import com.karim.booksapp.di.screens.main.MainScope
import com.karim.booksapp.di.screens.main.MainViewModelsModule
import com.karim.booksapp.screens.main.MainActivity
import com.karim.booksapp.screens.detail.DetailBookActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainViewModelsModule::class , MainFragmentBuildersModule::class , MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailViewModelsModule::class , DetailModule::class])
    abstract fun contributeDetailBookActivity(): DetailBookActivity


}
