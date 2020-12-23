package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.PostDomainModel

interface FetchPostsUseCase {
    suspend fun fetchPosts(): List<PostDomainModel>
}