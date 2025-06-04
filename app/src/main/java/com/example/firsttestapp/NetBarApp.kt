package com.example.firsttestapp

import android.app.Application
import com.example.firsttestapp.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NetBarApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NetBarApp)
            modules(koinModules)
        }
    }
}