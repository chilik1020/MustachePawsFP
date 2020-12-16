package com.chilik1020.data.repositories

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject

interface UserRepository {
    suspend fun login(loginRequestObject: LoginRequestObject): String
    suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDataModel
}