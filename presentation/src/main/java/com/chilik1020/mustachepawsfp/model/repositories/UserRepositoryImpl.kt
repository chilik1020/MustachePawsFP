package com.chilik1020.mustachepawsfp.model.repositories

import com.chilik1020.mustachepawsfp.model.entities.LoginRequestObject
import com.chilik1020.mustachepawsfp.model.entities.UserRequestObject
import com.chilik1020.mustachepawsfp.model.entities.UserVO
import com.chilik1020.mustachepawsfp.model.local.AppPreferences
import com.chilik1020.mustachepawsfp.model.remote.MustachePawsApi
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import toothpick.ktp.delegate.inject
import javax.inject.Inject

class UserRepositoryImpl : UserRepository {

    @Inject
    lateinit var api: MustachePawsApi

    @Inject
    lateinit var preferences: AppPreferences

    override fun login(loginRequestObject: LoginRequestObject): Observable<Response<ResponseBody>> {
        return api.login(loginRequestObject)
    }

    override fun echoDetails(): Observable<UserVO> {
        return api.echoDetails(preferences.accessToken as String)
    }

    override fun createUser(user: UserRequestObject): Observable<UserVO> {
        return api.createUser(user)
    }
}