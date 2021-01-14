package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.UserDomainModel

interface UserProfileByIdUseCase {
    suspend fun userProfileById(id: Long): UserDomainModel
}