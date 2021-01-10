package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.repositories.PostRepository
import javax.inject.Inject

class FetchPostByIdUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository
) : FetchPostByIdUseCase {
    override suspend fun fetchPost(id: Long): PostDomainModel {
        return postRepository.fetchPostById(id)
    }
}