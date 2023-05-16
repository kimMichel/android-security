package br.com.myapplication

import android.app.Application
import br.com.myapplication.shared.di.homeModule
import br.com.myapplication.shared.di.mainModule
import br.com.myapplication.shared.di.storeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinApp()
    }

    private fun startKoinApp() {
        val appModules = listOf(mainModule, homeModule, storeModule)
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}