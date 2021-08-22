package com.dnieln7.catdogs

import android.app.Application

class CatDogsApplication : Application() {

    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(applicationContext)
    }
}