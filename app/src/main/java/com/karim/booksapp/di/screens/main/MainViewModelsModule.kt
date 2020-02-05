package com.karim.booksapp.di.screens.main

import androidx.lifecycle.ViewModel
import com.karim.booksapp.di.ViewModelKey


import com.karim.booksapp.screens.main.viewmodels.MainViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel


}
