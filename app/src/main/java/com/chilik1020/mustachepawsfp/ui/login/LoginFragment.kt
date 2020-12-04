package com.chilik1020.mustachepawsfp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepawsfp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnLogin.setOnClickListener {
                val username = tietUsernameLoginF.text.toString()
                val password = tietPasswordLoginF.text.toString()
                viewModel.executeLogin(username, password)
            }
            btnSignUp.setOnClickListener { navigateToSignUpFragment() }
        }

        viewModel.viewState.observe(this) { render(it) }
    }

    private fun render(state: LoginViewState) {
        resetViews()
        when (state) {
            is LoginViewState.LoginLoadingState -> {
                binding.pbLoginLoading.visibility = View.VISIBLE
            }

            is LoginViewState.LoggedState -> {
                Toast.makeText(activity, "You have successfully logged in!", Toast.LENGTH_LONG)
                    .show()
                navigateToPostListFragment()
            }

            is LoginViewState.LoginErrorState -> {
                binding.tilUsernameLoginF.error = state.message
                binding.tilPasswordLoginF.error = " "
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is LoginViewState.UsernameErrorState -> {
                binding.tilUsernameLoginF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is LoginViewState.PasswordErrorState -> {
                binding.tilPasswordLoginF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun resetViews() {
        with(binding) {
            pbLoginLoading.visibility = View.GONE
            tilUsernameLoginF.error = null
            tilPasswordLoginF.error = null
        }
    }

    private fun navigateToSignUpFragment() {
    }

    private fun navigateToPostListFragment() {
    }
}
