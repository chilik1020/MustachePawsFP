package com.chilik1020.mustachepawsfp.model.remote

import com.chilik1020.mustachepawsfp.model.entities.LoginRequestObject
import com.chilik1020.mustachepawsfp.model.entities.UserRequestObject
import com.chilik1020.mustachepawsfp.model.entities.UserVO
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface MustachePawsApi {
    @POST("mustachepaws/login")
    @Headers("Content-Type: application/json")
    fun login(@Body user: LoginRequestObject): Observable<Response<ResponseBody>>

    @POST("mustachepaws/users/registration")
    fun createUser(@Body user: UserRequestObject): Observable<UserVO>

    @GET("mustachepaws/users/details")
    fun echoDetails(@Header("Authorization") authorization: String) : Observable<UserVO>
}