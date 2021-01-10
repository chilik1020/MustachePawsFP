package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.PostRequestObject
import com.chilik1020.domain.repositories.PostRepository
import javax.inject.Inject

class CreatePostUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository
) : CreatePostUseCase {
    override suspend fun createPost(post: PostRequestObject, imageUri: String) =
        postRepository.createPost(post, imageUri)

}