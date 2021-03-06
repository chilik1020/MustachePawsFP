package com.chilik1020.framework.remote

import com.chilik1020.data.models.PostDataModel
import com.chilik1020.data.models.UserDataModel
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.PostRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface MustachePawsApi {
    @POST("mustachepaws/login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body user: LoginRequestObject): Response<ResponseBody>

    @POST("mustachepaws/users/registration")
    suspend fun signUp(@Body user: SignUpRequestObject): UserDataModel

    @GET("mustachepaws/users/details")
    suspend fun echoDetails(@Header("Authorization") authorization: String): UserDataModel

    @GET("mustachepaws/users/save")
    suspend fun saveProfileData(
        @Header("Authorization") authorization: String,
        @Body user: UserDataModel
    ): UserDataModel

    @GET("mustachepaws/users/{id}")
    suspend fun getUserById(
        @Header("Authorization") authorization: String,
        @Path("id") id: Long
    ): UserDataModel

    @GET("mustachepaws/posts/all")
    suspend fun fetchPosts(@Header("Authorization") authorization: String): List<PostDataModel>

    @GET("mustachepaws/posts/one/{id}")
    suspend fun fetchPostById(
        @Header("Authorization") authorization: String,
        @Path("id") id: Long
    ): PostDataModel

    @GET("mustachepaws/posts/creator/{id}")
    suspend fun fetchPostByCreatorId(
        @Header("Authorization") authorization: String,
        @Path("id") id: Long
    ): List<PostDataModel>

    @POST("mustachepaws/posts/create")
    suspend fun createPost(
        @Header("Authorization") authorization: String,
        @Body post: PostRequestObject
    ): PostDataModel

    @Multipart
    @POST("mustachepaws/images/uploadImage")
    suspend fun uploadImage(
        @Header("Authorization") authorization: String,
        @Part file: MultipartBody.Part
    )
}