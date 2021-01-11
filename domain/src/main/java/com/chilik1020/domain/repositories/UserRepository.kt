package com.chilik1020.domain.repositories

import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import com.chilik1020.domain.models.UserDomainModel

interface UserRepository {
    suspend fun login(loginRequestObject: LoginRequestObject): String
    suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDomainModel
    suspend fun logout()
    suspend fun yourProfileDetails(): UserDomainModel
    suspend fun saveProfile(user: UserDomainModel): UserDomainModel
    suspend fun getUserById(id: Long): UserDomainModel
}