package com.chilik1020.data.sources

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject

interface UserRemoteDataSource {
    suspend fun login(loginRequestObject: LoginRequestObject): String
    suspend fun echoUserDetails(token: String): UserDataModel
    suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDataModel
}