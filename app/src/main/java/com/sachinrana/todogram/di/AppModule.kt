package com.sachinrana.todogram.di

import android.app.Application
import android.content.Context
import com.sachinrana.todogram.data.repository.TodoRepository
import com.sachinrana.todogram.factory.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    internal fun provideViewModelFactory(todoRepository: TodoRepository): ViewModelFactory {
        return ViewModelFactory(todoRepository)
    }
}