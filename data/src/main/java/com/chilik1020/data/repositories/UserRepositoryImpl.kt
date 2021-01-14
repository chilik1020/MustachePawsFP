package com.chilik1020.data.repositories

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.data.sources.UserRemoteDataSource
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import com.chilik1020.domain.models.UserDomainModel
import com.chilik1020.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
    private val toDomainMapper: (UserDataModel) -> UserDomainModel,
    private val toDataMapper: (UserDomainModel) -> UserDataModel
) : UserRepository {

    override suspend fun login(loginRequestObject: LoginRequestObject): String =
        withContext(Dispatchers.IO) {
            val accessToken = remoteDataSource.login(loginRequestObject)
            localDataSource.saveAccessToken(accessToken)
            val userDetail = remoteDataSource.echoUserDetails(accessToken)
            localDataSource.saveUserDetails(userDetail)
            return@withContext remoteDataSource.login(loginRequestObject)
        }

    override suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDomainModel =
        withContext(Dispatchers.IO) {
            return@withContext toDomainMapper.invoke(remoteDataSource.signUp(signUpRequestObject))
        }

    override suspend fun logout() {
        localDataSource.clear()
    }

    override suspend fun yourProfileDetails(): UserDomainModel =
        withContext(Dispatchers.IO) {
            return@withContext localDataSource.getSavedUserDetails().let(toDomainMapper)
        }

    override suspend fun saveProfile(user: UserDomainModel): UserDomainModel =
        withContext(Dispatchers.IO) {
            val accessToken = localDataSource.getSavedToken()
            val updatedUser = remoteDataSource.saveProfileData(accessToken, toDataMapper(user))
            localDataSource.saveUserDetails(updatedUser)
            return@withContext toDomainMapper(updatedUser)
        }

    override suspend fun getUserById(id: Long): UserDomainModel =
        withContext(Dispatchers.IO) {
            val accessToken = localDataSource.getSavedToken()
            val user = remoteDataSource.getUserById(accessToken, id)
            return@withContext toDomainMapper(user)
        }
}