package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.UserDomainModel
import com.chilik1020.domain.repositories.UserRepository
import javax.inject.Inject

class YourProfileDetailsUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : YourProfileDetailsUseCase {
    override suspend fun userDetails(): UserDomainModel {
        return userRepository.yourProfileDetails()
    }
}