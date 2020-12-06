package com.chilik1020.mustachepawsfp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chilik1020.mustachepawsfp.interactors.AuthInteractor
import com.chilik1020.mustachepawsfp.interactors.LoginInteractor
import com.chilik1020.mustachepawsfp.model.local.AppPreferences
import com.chilik1020.mustachepawsfp.utils.checkPasswordInLoginForm
import com.chilik1020.mustachepawsfp.utils.checkUsernameInLoginForm

class LoginViewModel(
    private val interactor: LoginInteractor,
    private val preferences: AppPreferences
) : ViewModel(),
    AuthInteractor.OnAuthFinishedListener,
    LoginInteractor.OnDetailsRetrievalFinishedListener {

    private val viewStateMutable = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState>
        get() = viewStateMutable

    fun executeLogin(username: String, password: String) {
        if (!isLoginFormCorrect(username, password)) return

        viewStateMutable.value = LoginViewState.LoginLoadingState
        interactor.login(username, password, this)
    }

    private fun isLoginFormCorrect(username: String, password: String): Boolean {
        val usernameErrorMessage = checkUsernameInLoginForm(username)
        if (usernameErrorMessage != null) {
            viewStateMutable.value = LoginViewState.UsernameErrorState(usernameErrorMessage)
            return false
        }

        val passwordErrorMessage = checkPasswordInLoginForm(password)
        if (passwordErrorMessage != null) {
            viewStateMutable.value = LoginViewState.PasswordErrorState(passwordErrorMessage)
            return false
        }

        return true
    }

    override fun onAuthSuccess() {
        interactor.persistAccessToken(preferences)
        interactor.retrieveDetails(preferences, this)
    }

    override fun onAuthError(message: String) {
        viewStateMutable.value = LoginViewState.LoginErrorState(message)
    }

    override fun onDetailsRetrievalSuccess() {
        interactor.persistUserDetails(preferences)
        viewStateMutable.value = LoginViewState.LoggedState
    }

    override fun onDetailsRetrievalError() {
        interactor.retrieveDetails(preferences, this)
    }
}