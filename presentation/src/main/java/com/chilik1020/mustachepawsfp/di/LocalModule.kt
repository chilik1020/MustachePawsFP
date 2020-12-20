package com.chilik1020.mustachepawsfp.di

import android.content.Context
import android.content.SharedPreferences
import com.chilik1020.framework.utils.PREFERENCE_FILE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_FILE_NAME, 0)
    }
}