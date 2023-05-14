package br.com.myapplication

import android.app.Application
import br.com.myapplication.shared.di.homeModule
import br.com.myapplication.shared.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}