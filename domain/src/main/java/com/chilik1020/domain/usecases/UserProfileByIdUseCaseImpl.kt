package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.UserDomainModel
import com.chilik1020.domain.repositories.UserRepository

class UserProfileByIdUseCaseImpl(private val userRepository: UserRepository) :
    UserProfileByIdUseCase {
    override suspend fun userProfileById(id: Long): UserDomainModel {
        return userRepository.getUserById(id)
    }
}