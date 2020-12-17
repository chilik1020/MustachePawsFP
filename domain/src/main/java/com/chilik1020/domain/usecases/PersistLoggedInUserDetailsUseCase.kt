package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.UserDomainModel

interface PersistLoggedInUserDetailsUseCase {
    suspend fun persistUserDetails(user: UserDomainModel)
}