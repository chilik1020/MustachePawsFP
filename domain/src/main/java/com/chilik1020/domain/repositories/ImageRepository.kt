package com.chilik1020.domain.repositories

interface ImageRepository {
    suspend fun uploadImage(imageUri: String)
}