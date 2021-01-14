package com.chilik1020.data.repositories

import com.chilik1020.data.sources.ImageRemoteDataSource
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.domain.repositories.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val imageRemoteDataSource: ImageRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : ImageRepository {
    override suspend fun uploadImage(imageUri: String) {
        withContext(Dispatchers.IO) {
            val token = userLocalDataSource.getSavedToken()
            imageRemoteDataSource.uploadImage(imageUri, token)
        }
    }
}