package com.chilik1020.framework.remote

import com.chilik1020.data.models.PostDataModel
import com.chilik1020.data.sources.PostRemoteDataSource
import com.chilik1020.domain.models.PostRequestObject
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val api: MustachePawsApi
) : PostRemoteDataSource {
    override suspend fun fetchPosts(token: String): List<PostDataModel> {
        return api.fetchPosts(token)
    }

    override suspend fun fetchPostById(token: String, id: Long): PostDataModel {
        return api.fetchPostById(token, id)
    }

    override suspend fun fetchPostByCreatorId(token: String, id: Long): List<PostDataModel> {
        return api.fetchPostByCreatorId(token, id)
    }

    override suspend fun createPost(
        post: PostRequestObject,
        imageUri: String,
        token: String
    ): PostDataModel {
        return api.createPost(token, post)
    }
}