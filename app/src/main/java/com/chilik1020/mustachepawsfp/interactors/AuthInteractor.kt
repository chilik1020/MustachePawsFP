package com.chilik1020.mustachepawsfp.interactors

import com.chilik1020.mustachepawsfp.model.entities.UserVO
import com.chilik1020.mustachepawsfp.model.local.AppPreferences

interface AuthInteractor {

    var userDetails: UserVO
    var accessToken: String
    var submittedUsername: String
    var submittedPassword: String

    interface OnAuthFinishedListener {
        fun onAuthSuccess()
        fun onAuthError(message: String)
    }

    fun persistAccessToken(preferences: AppPreferences)

    fun persistUserDetails(preferences: AppPreferences)
}