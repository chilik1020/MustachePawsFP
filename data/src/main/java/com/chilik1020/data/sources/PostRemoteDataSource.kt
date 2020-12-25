package com.chilik1020.data.sources

import com.chilik1020.data.models.ListPostDataModel

interface PostRemoteDataSource {
    suspend fun fetchPosts(token: String): ListPostDataModel
}