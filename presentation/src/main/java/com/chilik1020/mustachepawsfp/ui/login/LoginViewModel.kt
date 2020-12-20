package com.chilik1020.mustachepawsfp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilik1020.domain.models.LoginRequestObject
import com.chilik1020.domain.usecases.LoginUseCase
import com.chilik1020.mustachepawsfp.utils.checkPasswordInLoginForm
import com.chilik1020.mustachepawsfp.utils.checkUsernameInLoginForm
import javax.inject.Inject
import kotlinx.coroutines.launch

class LoginViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var loginUseCase: LoginUseCase

    private val viewStateMutable = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState>
        get() = viewStateMutable

    fun executeLogin(username: String, password: String) {
        if (!isLoginFormCorrect(username, password)) return

        viewStateMutable.value = LoginViewState.LoginLoadingState

        viewModelScope.launch {
            loginUseCase.login(LoginRequestObject(username, password))
        }
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
}