package com.naufalnibros.kagura.android

import android.app.Application
import com.naufalnibros.kagura.initKoin
import org.koin.android.ext.koin.androidContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
        }
    }
}