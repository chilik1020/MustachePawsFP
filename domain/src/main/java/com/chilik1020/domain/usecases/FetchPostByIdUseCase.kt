package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.PostDomainModel

interface FetchPostByIdUseCase {
    suspend fun fetchPost(id: Long): PostDomainModel
}