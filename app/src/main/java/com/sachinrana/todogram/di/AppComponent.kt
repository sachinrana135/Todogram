package com.sachinrana.todogram.di

import com.sachinrana.todogram.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ApiModule::class
    ]
)
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}
