package com.chilik1020.mustachepawsfp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentLoginBinding
import com.chilik1020.mustachepawsfp.di.ApplicationScope
import com.chilik1020.mustachepawsfp.di.LoginModule
import com.chilik1020.mustachepawsfp.di.LoginViewModelScope
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.lifecycle.closeOnDestroy
import toothpick.smoothie.viewmodel.closeOnViewModelCleared
import toothpick.smoothie.viewmodel.installViewModelBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by inject()

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
        injectDependencies()
        initViews()
    }

    private fun injectDependencies() {
        KTP.openScopes(ApplicationScope::class.java)
            .openSubScope(LoginViewModelScope::class.java) {
                it.installViewModelBinding<LoginViewModel>(this)
                    .installModules(LoginModule())
                    .closeOnViewModelCleared(this)
            }
            .closeOnDestroy(this)
            .inject(this)
    }

    private fun initViews() {
        with(binding) {
            btnLogin.setOnClickListener {
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
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    private fun navigateToPostListFragment() {
    }
}
