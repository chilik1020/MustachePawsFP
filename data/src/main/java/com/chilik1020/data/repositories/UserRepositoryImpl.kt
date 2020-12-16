package com.chilik1020.data.repositories

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.data.sources.UserRemoteDataSource
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import com.chilik1020.domain.models.UserDomainModel
import javax.inject.Inject

class UserRepositoryImpl : UserRepository {

    @Inject
    lateinit var localDataSource: UserLocalDataSource

    @Inject
    lateinit var remoteDataSource: UserRemoteDataSource

    @Inject
    lateinit var toDomainMapper: (UserDataModel) -> UserDomainModel

    @Inject
    lateinit var toDataMapper: (UserDomainModel) -> UserDataModel

    override suspend fun login(loginRequestObject: LoginRequestObject): String {
        return remoteDataSource.login(loginRequestObject)
    }

    override suspend fun echoUserDetails(token: String): UserDomainModel {
        return toDomainMapper.invoke(remoteDataSource.echoUserDetails(token))
    }

    override suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDomainModel {
        return toDomainMapper.invoke(remoteDataSource.signUp(signUpRequestObject))
    }

    override suspend fun saveAccessToken(token: String) {
        return localDataSource.saveAccessToken(token)
    }

    override suspend fun getSavedToken(): String? {
        return localDataSource.getSavedToken()
    }

    override suspend fun saveUserDetails(userDomainModel: UserDomainModel) {
        return localDataSource.saveUserDetails(toDataMapper.invoke(userDomainModel))
    }

    override suspend fun getSavedUserDetails(): UserDomainModel? {
        return localDataSource.getSavedUserDetails()?.let(toDomainMapper)
    }
}