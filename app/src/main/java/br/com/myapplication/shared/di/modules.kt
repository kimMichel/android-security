package br.com.myapplication.shared.di

import br.com.myapplication.repository.AppRepository
import br.com.myapplication.ui.home.HomeViewModel
import br.com.myapplication.ui.store.StoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { AppRepository() }
}

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}

val storeModule = module {
    viewModel { StoreViewModel(get()) }
}