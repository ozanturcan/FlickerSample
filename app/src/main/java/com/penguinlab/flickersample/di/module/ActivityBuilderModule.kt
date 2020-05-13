package com.penguinlab.flickersample.di.module

import com.penguinlab.flickersample.di.scope.ActivityScope
import com.penguinlab.flickersample.ui.MainActivity
import com.penguinlab.flickersample.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    fun bindMainActivity(): MainActivity


    @ActivityScope
    @ContributesAndroidInjector
    fun bindSplashActivity(): SplashActivity


}