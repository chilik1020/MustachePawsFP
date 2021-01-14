package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.UserDomainModel
import com.chilik1020.domain.repositories.UserRepository
import javax.inject.Inject

class SaveProfileUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : SaveProfileUseCase {
    override suspend fun saveProfile(user: UserDomainModel): UserDomainModel {
        return userRepository.saveProfile(user)
    }
}