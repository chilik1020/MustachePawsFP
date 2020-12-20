package com.chilik1020.mustachepawsfp.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ContextModule(private val application: Application) {

    @Binds
    @Singleton
    abstract fun bindContext(application: Application): Context
}