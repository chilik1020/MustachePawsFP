package com.chilik1020.framework.remote

import com.chilik1020.data.models.UserDataModel
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.models.SignUpRequestObject
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface MustachePawsApi {
    @POST("mustachepaws/login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body user: LoginRequestObject): Response<ResponseBody>

    @POST("mustachepaws/users/registration")
    suspend fun signUp(@Body user: SignUpRequestObject): UserDataModel

    @GET("mustachepaws/users/details")
    suspend fun echoDetails(@Header("Authorization") authorization: String): UserDataModel
}