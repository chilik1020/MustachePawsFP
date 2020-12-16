package com.chilik1020.mustachepawsfp.interactors

import com.chilik1020.mustachepawsfp.model.local.AppPreferences

interface LoginInteractor : AuthInteractor {

    interface OnDetailsRetrievalFinishedListener {
        fun onDetailsRetrievalSuccess()
        fun onDetailsRetrievalError()
    }

    fun login(username: String, password: String, listener: AuthInteractor.OnAuthFinishedListener)

    fun retrieveDetails(preferences: AppPreferences, listener: OnDetailsRetrievalFinishedListener)
}