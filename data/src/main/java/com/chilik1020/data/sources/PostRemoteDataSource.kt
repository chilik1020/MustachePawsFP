package com.chilik1020.data.sources

import com.chilik1020.data.models.PostDataModel

interface PostRemoteDataSource {
    suspend fun fetchPosts(token: String): List<PostDataModel>
}