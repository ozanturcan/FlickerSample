package com.penguinlab.flickersample.di.component

import android.content.Context
import com.penguinlab.data.di.module.DataModule
import com.penguinlab.flickersample.FlickerApplication
import com.penguinlab.flickersample.di.module.ActivityBuilderModule
import com.penguinlab.flickersample.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<FlickerApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AndroidInjector<FlickerApplication>
    }
}