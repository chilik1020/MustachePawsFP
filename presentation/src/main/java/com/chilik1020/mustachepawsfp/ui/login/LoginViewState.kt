package com.chilik1020.mustachepawsfp.ui.login

sealed class LoginViewState {
    object LoginLoadingState : LoginViewState()
    object LoggedState : LoginViewState()
    class LoginErrorState(val message: String) : LoginViewState()
    class UsernameErrorState(val message: String) : LoginViewState()
    class PasswordErrorState(val message: String) : LoginViewState()
}