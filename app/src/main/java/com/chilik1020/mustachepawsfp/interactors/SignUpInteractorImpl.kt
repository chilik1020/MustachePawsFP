package com.chilik1020.mustachepawsfp.interactors

import com.chilik1020.mustachepawsfp.model.entities.LoginRequestObject
import com.chilik1020.mustachepawsfp.model.entities.UserRequestObject
import com.chilik1020.mustachepawsfp.model.entities.UserVO
import com.chilik1020.mustachepawsfp.model.local.AppPreferences
import com.chilik1020.mustachepawsfp.model.repositories.UserRepository
import com.chilik1020.mustachepawsfp.utils.getMessageFromThrowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpInteractorImpl(private val userRepository: UserRepository) : SignUpInteractor {

    override lateinit var userDetails: UserVO
    override lateinit var accessToken: String
    override lateinit var submittedUsername: String
    override lateinit var submittedPassword: String

    override fun signUp(
        user: UserRequestObject,
        listener: SignUpInteractor.OnSignUpFinishedListener
    ) {
        submittedUsername = user.username
        submittedPassword = user.password

        val subscribe = userRepository.createUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { res ->
                    userDetails = res
                    listener.onSuccess()
                },
                { error ->
                    val message = getMessageFromThrowable(error)
                    listener.onError(message)
                    error.printStackTrace()
                })
    }

    override fun getAuthorization(listener: AuthInteractor.OnAuthFinishedListener) {
        val loginRequestObject = LoginRequestObject(submittedUsername, submittedPassword)
        val subscribe = userRepository.login(loginRequestObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { res ->
                    accessToken = res.headers()["Authorization"] as String
                    listener.onAuthSuccess()
                },
                { error ->
                    val message = getMessageFromThrowable(error)
                    listener.onAuthError(message)
                    error.printStackTrace()
                })
    }

    override fun persistAccessToken(preferences: AppPreferences) {
        preferences.storeAccessToken(accessToken)
    }

    override fun persistUserDetails(preferences: AppPreferences) {
        preferences.storeUserDetails(userDetails)
    }
}