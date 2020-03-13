package com.sachinrana.todogram

import android.app.Application
import com.sachinrana.todogram.di.AppComponent
import com.sachinrana.todogram.di.AppModule
import com.sachinrana.todogram.di.DaggerAppComponent

class TodoApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: TodoApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

}