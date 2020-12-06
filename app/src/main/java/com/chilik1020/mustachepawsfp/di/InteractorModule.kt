package com.chilik1020.mustachepawsfp.di

import com.chilik1020.mustachepawsfp.interactors.LoginInteractor
import com.chilik1020.mustachepawsfp.interactors.LoginInteractorImpl
import com.chilik1020.mustachepawsfp.interactors.SignUpInteractor
import com.chilik1020.mustachepawsfp.interactors.SignUpInteractorImpl
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class InteractorModule : Module() {

    init {
        bind(LoginInteractor::class.java).to(LoginInteractorImpl::class.java)
        bind(SignUpInteractor::class.java).to(SignUpInteractorImpl::class.java)
    }
}