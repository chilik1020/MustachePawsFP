package com.chilik1020.mustachepawsfp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentLoginBinding
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.chilik1020.mustachepawsfp.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        with(binding) {
            btnLogin.setOnClickListener {
                hideKeyboard()
                val username = tietUsernameLoginF.text.toString()
                val password = tietPasswordLoginF.text.toString()
                viewModel.executeLogin(username, password)
            }
            btnSignUp.setOnClickListener { navigateToSignUpFragment() }
        }

        viewModel.viewState.observe(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: LoginViewState) {
        resetViews()
        when (state) {
            is LoginViewState.LoginLoadingState -> {
                binding.pbLoginLoading.visibility = View.VISIBLE
            }

            is LoginViewState.LoggedState -> {
                showSnackBarMessage("You have successfully logged in!")
                navigateToPostListFragment()
            }

            is LoginViewState.LoginErrorState -> {
                binding.tilUsernameLoginF.error = state.message
                binding.tilPasswordLoginF.error = " "
                showSnackBarMessage(state.message)
            }

            is LoginViewState.UsernameErrorState -> {
                binding.tilUsernameLoginF.error = state.message
                showSnackBarMessage(state.message)
            }

            is LoginViewState.PasswordErrorState -> {
                binding.tilPasswordLoginF.error = state.message
                showSnackBarMessage(state.message)
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
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    private fun navigateToPostListFragment() {
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}
