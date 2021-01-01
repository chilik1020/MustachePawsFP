package com.chilik1020.domain.usecases

import com.chilik1020.domain.repositories.ImageRepository

class UploadImageUseCaseImpl(
    private val imageRepository: ImageRepository
) : UploadImageUseCase {
    override suspend fun uploadImage(imageUri: String) {
        imageRepository.uploadImage(imageUri)
    }
}