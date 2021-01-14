package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.repositories.PostRepository
import javax.inject.Inject

class FetchPostByCreatorIdUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository
) : FetchPostByCreatorIdUseCase {
    override suspend fun fetchPost(id: Long): List<PostDomainModel> {
        return postRepository.fetchPostByCreatorId(id)
    }
}