package com.chilik1020.data.sources

interface ImageRemoteDataSource {
    suspend fun uploadImage(
        imageUri: String,
        token: String
    )
}