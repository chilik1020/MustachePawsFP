package com.chilik1020.framework.di

import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.data.sources.UserRemoteDataSource
import com.chilik1020.framework.local.UserLocalDataSourceImpl
import com.chilik1020.framework.remote.UserRemoteDataSourceImpl
import toothpick.config.Module

class DataModule : Module() {
    init {
        bind(UserLocalDataSource::class.java).to(UserLocalDataSourceImpl::class.java)
        bind(UserRemoteDataSource::class.java).to(UserRemoteDataSourceImpl::class.java)
    }
}