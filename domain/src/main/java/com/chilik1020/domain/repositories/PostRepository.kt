package com.chilik1020.domain.repositories

import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.models.PostRequestObject

interface PostRepository {
    suspend fun fetchPosts(): List<PostDomainModel>
    suspend fun createPost(post: PostRequestObject, imageUri: String): PostDomainModel
}