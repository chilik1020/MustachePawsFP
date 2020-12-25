package com.chilik1020.framework.remote

import com.chilik1020.data.models.ListPostDataModel
import com.chilik1020.data.sources.PostRemoteDataSource
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val api: MustachePawsApi
) : PostRemoteDataSource {
    override suspend fun fetchPosts(token: String): ListPostDataModel {
        return api.fetchPosts(token)
    }
}