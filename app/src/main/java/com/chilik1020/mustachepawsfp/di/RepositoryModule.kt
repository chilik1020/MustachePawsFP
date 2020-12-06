package com.chilik1020.mustachepawsfp.di

import com.chilik1020.mustachepawsfp.model.repositories.UserRepository
import com.chilik1020.mustachepawsfp.model.repositories.UserRepositoryImpl
import toothpick.config.Module
import javax.inject.Singleton

@Singleton
class RepositoryModule : Module() {

    init {
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
    }
}