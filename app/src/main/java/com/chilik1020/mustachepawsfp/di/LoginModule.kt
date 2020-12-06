package com.chilik1020.mustachepawsfp.di

import com.chilik1020.mustachepawsfp.interactors.LoginInteractor
import com.chilik1020.mustachepawsfp.interactors.LoginInteractorImpl
import com.chilik1020.mustachepawsfp.model.repositories.UserRepository
import com.chilik1020.mustachepawsfp.model.repositories.UserRepositoryImpl
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class LoginModule : Module() {
    init {
        bind(LoginInteractor::class.java).to(LoginInteractorImpl::class.java)
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
    }
}