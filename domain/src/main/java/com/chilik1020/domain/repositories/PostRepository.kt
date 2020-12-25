package com.chilik1020.domain.repositories

import com.chilik1020.domain.models.PostDomainModel

interface PostRepository {
    suspend fun fetchPosts() : List<PostDomainModel>
}