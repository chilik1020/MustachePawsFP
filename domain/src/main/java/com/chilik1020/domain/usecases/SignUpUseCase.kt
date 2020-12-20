package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.SignUpRequestObject
import com.chilik1020.domain.models.UserDomainModel

interface SignUpUseCase {
    suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDomainModel
}