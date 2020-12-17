package com.chilik1020.mustachepawsfp.ui.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.usecases.LoginUseCase
import com.chilik1020.framework.di.ApplicationScope
import com.chilik1020.mustachepawsfp.interactors.AuthInteractor
import com.chilik1020.mustachepawsfp.interactors.LoginInteractor
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.chilik1020.mustachepawsfp.utils.checkPasswordInLoginForm
import com.chilik1020.mustachepawsfp.utils.checkUsernameInLoginForm
import kotlinx.coroutines.launch
import toothpick.ktp.KTP
import javax.inject.Inject

class LoginViewModel : ViewModel(),
    AuthInteractor.OnAuthFinishedListener,
    LoginInteractor.OnDetailsRetrievalFinishedListener {

//    @Inject
//    lateinit var interactor: LoginInteractor

    @Inject
    lateinit var preferences: SharedPreferences

    @Inject
    lateinit var loginUseCase: LoginUseCase

    private val viewStateMutable = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState>
        get() = viewStateMutable

    fun executeLogin(username: String, password: String) {
        if (!isLoginFormCorrect(username, password)) return

        viewStateMutable.value = LoginViewState.LoginLoadingState

        viewModelScope.launch {
//            loginUseCase.login(LoginRequestObject(username, password))
        }
//        interactor.login(username, password, this)
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
//        interactor.persistAccessToken(preferences)
//        interactor.retrieveDetails(preferences, this)
    }

    override fun onAuthError(message: String) {
        viewStateMutable.value = LoginViewState.LoginErrorState(message)
    }

    override fun onDetailsRetrievalSuccess() {
//        interactor.persistUserDetails(preferences)
        viewStateMutable.value = LoginViewState.LoggedState
    }

    override fun onDetailsRetrievalError() {
//        interactor.retrieveDetails(preferences, this)
    }
}