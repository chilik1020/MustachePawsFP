package com.chilik1020.mustachepawsfp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chilik1020.mustachepawsfp.interactors.AuthInteractor
import com.chilik1020.mustachepawsfp.interactors.SignUpInteractor
import com.chilik1020.mustachepawsfp.model.entities.UserRequestObject
import com.chilik1020.mustachepawsfp.model.local.AppPreferences
import com.chilik1020.mustachepawsfp.utils.checkConfirmPasswordInSignUpForm
import com.chilik1020.mustachepawsfp.utils.checkEmailInSignUpForm
import com.chilik1020.mustachepawsfp.utils.checkPasswordInSignUpForm
import com.chilik1020.mustachepawsfp.utils.checkUsernameInSignUpForm

class SignUpViewModel(
    private val interactor: SignUpInteractor,
    private val preferences: AppPreferences
) : ViewModel(),
    AuthInteractor.OnAuthFinishedListener,
    SignUpInteractor.OnSignUpFinishedListener {

    private val viewStateMutable = MutableLiveData<SignUpViewState>()
    val viewState: LiveData<SignUpViewState>
        get() = viewStateMutable

    fun executeSignUp(username: String, email: String, password: String, confirmPassword: String) {
        if (!isSignUpFormCorrect(username, email, password, confirmPassword)) return

        viewStateMutable.value = SignUpViewState.SignUpLoadingState
        interactor.signUp(
            UserRequestObject(
                username,
                "",
                "",
                email,
                "",
                password
            ), this
        )
    }

    private fun isSignUpFormCorrect(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val usernameErrorMessage = checkUsernameInSignUpForm(username)
        if (usernameErrorMessage != null) {
            viewStateMutable.value = SignUpViewState.UsernameErrorState(usernameErrorMessage)
            return false
        }

        val emailErrorMessage = checkEmailInSignUpForm(email)
        if (emailErrorMessage != null) {
            viewStateMutable.value = SignUpViewState.EmailErrorState(emailErrorMessage)
            return false
        }

        val passwordErrorMessage = checkPasswordInSignUpForm(password)
        if (passwordErrorMessage != null) {
            viewStateMutable.value = SignUpViewState.PasswordErrorState(passwordErrorMessage)
            return false
        }

        val confirmPasswordErrorMessage =
            checkConfirmPasswordInSignUpForm(password, confirmPassword)
        if (confirmPasswordErrorMessage != null) {
            viewStateMutable.value =
                SignUpViewState.ConfirmPasswordErrorState(confirmPasswordErrorMessage)
            return false
        }

        return true
    }

    override fun onSuccess() {
        interactor.getAuthorization(this)
    }

    override fun onAuthSuccess() {
        interactor.persistAccessToken(preferences)
        interactor.persistUserDetails(preferences)
        viewStateMutable.value = SignUpViewState.SignUpFinishedState
    }

    override fun onAuthError(message: String) {
        viewStateMutable.value = SignUpViewState.SignUpErrorState(message)
    }

    override fun onError(message: String) {
        viewStateMutable.value = SignUpViewState.SignUpErrorState(message)
    }
}