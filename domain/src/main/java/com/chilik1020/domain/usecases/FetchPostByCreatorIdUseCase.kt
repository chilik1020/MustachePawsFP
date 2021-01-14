package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.PostDomainModel

interface FetchPostByCreatorIdUseCase {
    suspend fun fetchPost(id: Long): List<PostDomainModel>
}