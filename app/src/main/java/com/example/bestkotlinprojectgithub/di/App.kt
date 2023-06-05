package com.example.bestkotlinprojectgithub.di

import android.app.Application
import com.example.bestkotlinprojectgithub.di.DataModule
import com.example.bestkotlinprojectgithub.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        PresentationModule.load()
    }
}