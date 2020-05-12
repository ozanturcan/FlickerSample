package com.penguinlab.flickersample.di.module

import com.penguinlab.flickersample.di.scope.FragmentScope
import com.penguinlab.flickersample.ui.detail.ImageDetailFragment
import com.penguinlab.flickersample.ui.getrecent.RecentFeedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun bindRecentFeedFragment(): RecentFeedFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun bindImageDetailFragment(): ImageDetailFragment
}