package com.chilik1020.mustachepawsfp.model.repositories

import com.chilik1020.mustachepawsfp.model.entities.LoginRequestObject
import com.chilik1020.mustachepawsfp.model.entities.UserRequestObject
import com.chilik1020.mustachepawsfp.model.entities.UserVO
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body

interface UserRepository {
    fun login(loginRequestObject: LoginRequestObject) : Observable<Response<ResponseBody>>
    fun echoDetails(): Observable<UserVO>
    fun createUser(@Body user: UserRequestObject): Observable<UserVO>
}