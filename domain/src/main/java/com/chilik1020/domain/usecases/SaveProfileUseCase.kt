package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.UserDomainModel

interface SaveProfileUseCase {
    suspend fun saveProfile(user: UserDomainModel): UserDomainModel
}