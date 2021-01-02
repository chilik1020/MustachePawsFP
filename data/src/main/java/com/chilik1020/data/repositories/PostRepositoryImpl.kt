package com.chilik1020.data.repositories

import com.chilik1020.data.models.PostDataModel
import com.chilik1020.data.sources.PostRemoteDataSource
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.domain.models.PostDomainModel
import com.chilik1020.domain.models.PostRequestObject
import com.chilik1020.domain.repositories.PostRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val postRemoteDataSource: PostRemoteDataSource,
    private val toDomainMapper: (PostDataModel) -> PostDomainModel
) : PostRepository {
    override suspend fun fetchPosts(): List<PostDomainModel> =
        withContext(Dispatchers.IO) {
            val token = userLocalDataSource.getSavedToken()
            return@withContext postRemoteDataSource.fetchPosts(token)
                .map { toDomainMapper.invoke(it) }
        }

    override suspend fun fetchPostById(id: Long): PostDomainModel =
        withContext(Dispatchers.IO) {
            val token = userLocalDataSource.getSavedToken()
            return@withContext toDomainMapper.invoke(postRemoteDataSource.fetchPostById(token, id))
        }

    override suspend fun fetchPostByCreatorId(id: Long): List<PostDomainModel> =
        withContext(Dispatchers.IO) {
            val token = userLocalDataSource.getSavedToken()
            return@withContext postRemoteDataSource.fetchPostByCreatorId(token, id)
                .map { toDomainMapper.invoke(it) }
        }

    override suspend fun createPost(post: PostRequestObject, imageUri: String): PostDomainModel =
        withContext(Dispatchers.IO) {
            val token = userLocalDataSource.getSavedToken()
            val postCreated = postRemoteDataSource.createPost(post, imageUri, token)
            return@withContext toDomainMapper.invoke(postCreated)
        }
}
