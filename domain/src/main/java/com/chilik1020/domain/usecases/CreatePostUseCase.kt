package com.chilik1020.domain.usecases

import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.models.PostRequestObject

interface CreatePostUseCase {
    suspend fun createPost(post:PostRequestObject, imageUri: String):PostDomainModel
}