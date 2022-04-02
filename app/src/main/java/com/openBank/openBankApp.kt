package com.openBank


import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.openBank.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class openBankApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        // Start Koin
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@openBankApp)
            modules(appModule, repositoryModule, networkModule, ladApiModule, localStorageModule)

        }
    }
}