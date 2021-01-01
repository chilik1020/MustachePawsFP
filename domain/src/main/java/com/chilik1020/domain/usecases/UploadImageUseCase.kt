package com.chilik1020.domain.usecases

interface UploadImageUseCase {
    suspend fun uploadImage(imageUri: String)
}