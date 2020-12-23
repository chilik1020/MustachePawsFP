package com.chilik1020.mustachepawsfp.di

import android.app.Application
import com.chilik1020.mustachepawsfp.MustachePawsDaggerApplication
import com.chilik1020.mustachepawsfp.di.viewModels.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        LocalModule::class,
        NetworkModule::class,
        UserModule::class,
        PostModule::class
    ]
)
interface AppComponent : AndroidInjector<MustachePawsDaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}