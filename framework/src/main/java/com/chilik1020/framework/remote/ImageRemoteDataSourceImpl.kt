package com.chilik1020.framework.remote

import com.chilik1020.data.sources.ImageRemoteDataSource
import java.io.File
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ImageRemoteDataSourceImpl(
    private val api: MustachePawsApi
) : ImageRemoteDataSource {
    override suspend fun uploadImage(imageUri: String, token: String) {
        val imageFile = File(imageUri)
        val fileRequestBody = RequestBody.create(MediaType.parse("image/*"), imageFile)
        val imageMultiPart =
            MultipartBody.Part.createFormData("image", imageFile.name, fileRequestBody)
        api.uploadImage(token, imageMultiPart)
    }
}