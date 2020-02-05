package com.karim.booksapp.di.screens.detail

import androidx.lifecycle.ViewModel
import com.karim.booksapp.di.ViewModelKey
import com.karim.booksapp.screens.detail.viewmodels.DetailViewModel


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel


}
