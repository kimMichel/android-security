package br.com.myapplication.shared.di

import PassRoomDb
import androidx.room.Room
import br.com.myapplication.database.dao.PasswordDao
import br.com.myapplication.repository.AppRepository
import br.com.myapplication.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PassRoomDb::class.java,
            "password_db"
        ).build()
    }
    single<PasswordDao> {
        val database = get<PassRoomDb>()
        database.passDao()
    }
    single { AppRepository(get()) }
}

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}