package com.bradarius.algebraoglednopredavanje

import android.app.Application
import com.bradarius.algebraoglednopredavanje.login.service.appModules
import com.bradarius.algebraoglednopredavanje.login.service.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModules, viewModelModule))
        }
    }
}