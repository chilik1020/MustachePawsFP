package com.chilik1020.data.repositories

import com.chilik1020.data.models.PostDataModel
import com.chilik1020.data.sources.PostRemoteDataSource
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.repositories.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val postRemoteDataSource: PostRemoteDataSource,
    private val toDomainMapper: (PostDataModel) -> PostDomainModel
) : PostRepository {
    override suspend fun fetchPosts(): List<PostDomainModel> {
        val token = userLocalDataSource.getSavedToken()
        return if (token != null) {
            val posts = postRemoteDataSource.fetchPosts(token)
            val result = mutableListOf<PostDomainModel>()
            posts.data.forEach {
                result.add(toDomainMapper(it))
            }
            result
        } else {
            emptyList()
        }
    }
}