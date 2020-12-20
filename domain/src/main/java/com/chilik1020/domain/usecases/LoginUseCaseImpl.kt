package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.repositories.UserRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : LoginUseCase {

    override suspend fun login(loginRequestObject: LoginRequestObject): String {
        return userRepository.login(loginRequestObject)
    }
}