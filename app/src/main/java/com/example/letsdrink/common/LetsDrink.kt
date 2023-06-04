package com.example.letsdrink.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.letsdrink.di.dataModule
import com.example.letsdrink.di.domainModule
import com.example.letsdrink.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

val mainModules = listOf(presentationModule, domainModule, dataModule)

class LetsDrink : Application() {

    override fun onCreate() {
        super.onCreate()
        LetsDrink.instance = applicationContext
        init()
    }

    private fun init() {
        initKoin()
    }


    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(mainModules)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: Context? = null
            private set
    }
}



