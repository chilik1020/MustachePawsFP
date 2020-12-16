package com.chilik1020.mustachepawsfp.interactors

import com.chilik1020.mustachepawsfp.model.entities.UserRequestObject


interface SignUpInteractor : AuthInteractor {

    interface OnSignUpFinishedListener {
        fun onSuccess()
        fun onError(message: String)
    }

    fun signUp(user: UserRequestObject, listener: OnSignUpFinishedListener)

    fun getAuthorization(listener: AuthInteractor.OnAuthFinishedListener)
}