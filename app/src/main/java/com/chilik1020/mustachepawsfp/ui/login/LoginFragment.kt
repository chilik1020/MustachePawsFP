package com.chilik1020.mustachepawsfp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepawsfp.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener {
            val username = tietUsernameLoginF.text.toString()
            val password = tietPasswordLoginF.text.toString()
            viewModel.executeLogin(username, password)
        }
        btnSignUp.setOnClickListener { navigateToSignUpFragment() }
        viewModel.viewState.observe(this) { render(it) }
    }

    private fun render(state: LoginViewState) {
        resetViews()
        when (state) {
            is LoginViewState.LoginLoadingState -> {
                pbLoginLoading.visibility = View.VISIBLE
            }

            is LoginViewState.LoggedState -> {
                Toast.makeText(activity, "You have successfully logged in!", Toast.LENGTH_LONG)
                    .show()
                navigateToPostListFragment()
            }

            is LoginViewState.LoginErrorState -> {
                tilUsernameLoginF.error = state.message
                tilPasswordLoginF.error = " "
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is LoginViewState.UsernameErrorState -> {
                tilUsernameLoginF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is LoginViewState.PasswordErrorState -> {
                tilPasswordLoginF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun resetViews() {
        pbLoginLoading.visibility = View.GONE
        tilUsernameLoginF.error = null
        tilPasswordLoginF.error = null
    }

    private fun navigateToSignUpFragment() {
    }

    private fun navigateToPostListFragment() {
    }
}
