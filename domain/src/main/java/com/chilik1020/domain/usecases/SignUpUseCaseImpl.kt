package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.SignUpRequestObject
import com.chilik1020.domain.models.UserDomainModel
import com.chilik1020.domain.repositories.UserRepository
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : SignUpUseCase {
    override suspend fun signUp(signUpRequestObject: SignUpRequestObject): UserDomainModel {
        return userRepository.signUp(signUpRequestObject)
    }
}