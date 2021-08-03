package com.example.moviex

import android.app.Application
import com.example.moviex.di.applicationModule
import com.example.moviex.di.networkModule
import com.example.moviex.di.repositoryModule
import com.example.moviex.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            printLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            modules(applicationModule, viewModelModule, networkModule, repositoryModule)
        }
    }
}
