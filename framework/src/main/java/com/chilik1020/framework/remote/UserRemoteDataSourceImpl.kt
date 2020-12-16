package com.chilik1020.framework.remote

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.data.sources.UserRemoteDataSource
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import javax.inject.Inject

class UserRemoteDataSourceImpl : UserRemoteDataSource {

    @Inject
    lateinit var api: MustachePawsApi

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
}