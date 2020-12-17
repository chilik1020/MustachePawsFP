package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCaseImpl : LoginUseCase {

    @Inject
    lateinit var userRepository: UserRepository

    override suspend fun login(loginRequestObject: LoginRequestObject): String {
        return userRepository.login(loginRequestObject)
    }
}