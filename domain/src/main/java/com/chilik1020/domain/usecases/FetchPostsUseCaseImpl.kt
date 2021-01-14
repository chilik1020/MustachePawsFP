package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.repositories.PostRepository
import javax.inject.Inject

class FetchPostsUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository
) : FetchPostsUseCase {
    override suspend fun fetchPosts(): List<PostDomainModel> =
        postRepository.fetchPosts()

}