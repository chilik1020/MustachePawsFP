package com.chilik1020.framework.di

import com.chilik1020.data.repositories.UserRepositoryImpl
import com.chilik1020.domain.repositories.UserRepository
import com.chilik1020.domain.usecases.LoginUseCase
import com.chilik1020.domain.usecases.LoginUseCaseImpl
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class LoginModule : Module() {
    init {
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
        bind(LoginUseCase::class.java).to(LoginUseCaseImpl::class.java)
    }
}