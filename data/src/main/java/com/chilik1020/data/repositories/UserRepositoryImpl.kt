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
    private val toDomainMapper: (UserDataModel) -> UserDomainModel
) : UserRepository {

    override suspend fun login(loginRequestObject: LoginRequestObject): String {
        val accessToken = remoteDataSource.login(loginRequestObject)
        localDataSource.saveAccessToken(accessToken)
        val userDetail = remoteDataSource.echoUserDetails(accessToken)
        localDataSource.saveUserDetails(userDetail)
        return remoteDataSource.login(loginRequestObject)
    }

    override suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDomainModel {
        return toDomainMapper.invoke(remoteDataSource.signUp(signUpRequestObject))
    }

    override suspend fun logout() {
        localDataSource.clear()
    }

    override suspend fun yourProfileDetails(): UserDomainModel {
        return localDataSource.getSavedUserDetails().let(toDomainMapper)
    }
}