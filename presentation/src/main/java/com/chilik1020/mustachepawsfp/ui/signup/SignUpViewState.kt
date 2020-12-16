package com.chilik1020.mustachepawsfp.ui.signup

sealed class SignUpViewState {
    object SignUpLoadingState : SignUpViewState()
    object SignUpFinishedState : SignUpViewState()
    class SignUpErrorState(val message: String) : SignUpViewState()
    class UsernameErrorState(val message: String) : SignUpViewState()
    class EmailErrorState(val message: String) : SignUpViewState()
    class PasswordErrorState(val message: String) : SignUpViewState()
    class ConfirmPasswordErrorState(val message: String) : SignUpViewState()
}