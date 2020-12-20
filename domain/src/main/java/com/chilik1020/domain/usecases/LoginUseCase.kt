package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.LoginRequestObject

interface LoginUseCase {
    suspend fun login(loginRequestObject: LoginRequestObject): String
}