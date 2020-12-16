package com.chilik1020.data.repositories

import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import com.chilik1020.domain.models.UserDomainModel

interface UserRepository {
    suspend fun login(loginRequestObject: LoginRequestObject): String
    suspend fun echoUserDetails(token: String): UserDomainModel
    suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDomainModel
    suspend fun saveAccessToken(token: String)
    suspend fun getSavedToken(): String?
    suspend fun saveUserDetails(userDomainModel: UserDomainModel)
    suspend fun getSavedUserDetails(): UserDomainModel?
}