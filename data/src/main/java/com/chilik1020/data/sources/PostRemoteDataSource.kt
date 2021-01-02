package com.chilik1020.data.sources

import com.chilik1020.data.models.PostDataModel
import com.chilik1020.domain.models.PostRequestObject

interface PostRemoteDataSource {
    suspend fun fetchPosts(token: String): List<PostDataModel>
    suspend fun fetchPostById(token: String, id: Long): PostDataModel
    suspend fun fetchPostByCreatorId(token: String, id: Long): List<PostDataModel>
    suspend fun createPost(
        post: PostRequestObject,
        imageUri: String,
        token: String
    ): PostDataModel
}