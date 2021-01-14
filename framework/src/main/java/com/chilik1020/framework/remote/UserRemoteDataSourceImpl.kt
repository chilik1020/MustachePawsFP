package com.chilik1020.framework.remote

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.data.sources.UserRemoteDataSource
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val api: MustachePawsApi
) : UserRemoteDataSource {

    override suspend fun login(loginRequestObject: LoginRequestObject): String {
        val response = api.login(loginRequestObject)
        return response.headers()["Authorization"] as String
    }

    override suspend fun echoUserDetails(token: String): UserDataModel {
        return api.echoDetails(token)
    }

    override suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDataModel {
        return api.signUp(signUpRequestObject)
    }

    override suspend fun saveProfileData(
        token: String,
        userDataModel: UserDataModel
    ): UserDataModel {
        return api.saveProfileData(token, userDataModel)
    }

    override suspend fun getUserById(token: String, id: Long): UserDataModel {
        return api.getUserById(token, id)
    }
}