package com.chilik1020.domain.usecase

import com.chilik1020.domain.models.SignUpRequestObject

interface SignUpUseCase {
    suspend fun signUp(signUpRequestObject: SignUpRequestObject)
}