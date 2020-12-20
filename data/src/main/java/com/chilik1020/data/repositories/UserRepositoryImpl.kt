package com.chilik1020.data.repositories

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.data.sources.UserRemoteDataSource
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import com.chilik1020.domain.models.UserDomainModel
import com.chilik1020.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
    private val toDomainMapper: (UserDataModel) -> UserDomainModel,
    private val toDataMapper: (UserDomainModel) -> UserDataModel
) : UserRepository {

    override suspend fun login(loginRequestObject: LoginRequestObject): String {
        return remoteDataSource.login(loginRequestObject)
    }

    override suspend fun echoUserDetails(): UserDomainModel {
        return toDomainMapper.invoke(remoteDataSource.echoUserDetails("token"))
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