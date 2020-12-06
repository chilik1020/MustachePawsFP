package com.chilik1020.mustachepawsfp.di

import com.chilik1020.mustachepawsfp.interactors.SignUpInteractor
import com.chilik1020.mustachepawsfp.interactors.SignUpInteractorImpl
import com.chilik1020.mustachepawsfp.model.repositories.UserRepository
import com.chilik1020.mustachepawsfp.model.repositories.UserRepositoryImpl
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class SignUpModule : Module() {
    init {
        bind(SignUpInteractor::class.java).to(SignUpInteractorImpl::class.java)
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
    }
}