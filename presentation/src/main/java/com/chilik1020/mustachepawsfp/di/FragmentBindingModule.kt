package com.chilik1020.mustachepawsfp.di

import com.chilik1020.mustachepawsfp.ui.login.LoginFragment
import com.chilik1020.mustachepawsfp.ui.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun signUpFragment(): SignUpFragment
}