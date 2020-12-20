package com.chilik1020.mustachepawsfp.di

import com.chilik1020.mustachepawsfp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}