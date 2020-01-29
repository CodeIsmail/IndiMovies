package com.idealorb.tracketv

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TvShowApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            //add context
            androidContext(this@TvShowApplication)
            //load up the modules
            modules(viewmodelModule, usecaseModule, repositoryModule, networkModule)
        }
    }
}