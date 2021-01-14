package com.chilik1020.mustachepawsfp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chilik1020.domain.models.SignUpRequestObject
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentSignUpBinding
import com.chilik1020.mustachepawsfp.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SignUpFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentSignUpBinding

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            btnSignUp.setOnClickListener {
                hideKeyboard()
                viewModel.executeSignUp(
                    SignUpRequestObject(
                        tietUsernameSignUpF.text.toString(),
                        tietFirstnameSignUpF.text.toString(),
                        tietLastnameSignUpF.text.toString(),
                        tietEmailSignUpF.text.toString(),
                        tietPhonenumberSignUpF.text.toString(),
                        "not defined",
                        tietPasswordSignUpF.text.toString()
                    ),
                    tietConfirmPasswordSignUpF.text.toString()
                )
            }
        }
        viewModel.viewState.observe(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: SignUpViewState) {
        resetViews()
        when (state) {
            is SignUpViewState.SignUpLoadingState -> {
                binding.pbSignUpLoading.visibility = View.VISIBLE
            }

            is SignUpViewState.SignUpFinishedState -> {
                showSnackBarMessage("You have successfully registered!")
                navigateToLoginFragment()
            }

            is SignUpViewState.SignUpErrorState -> {
                showSnackBarMessage(state.message)
            }

            is SignUpViewState.UsernameErrorState -> {
                binding.tilUsernameSignUpF.error = state.message
                showSnackBarMessage(state.message)
            }

            is SignUpViewState.EmailErrorState -> {
                binding.tilEmailSignUpF.error = state.message
                showSnackBarMessage(state.message)
            }

            is SignUpViewState.PasswordErrorState -> {
                binding.tilPasswordSignUpF.error = state.message
                showSnackBarMessage(state.message)
            }

            is SignUpViewState.ConfirmPasswordErrorState -> {
                binding.tilConfirmPasswordSignUpF.error = state.message
                showSnackBarMessage(state.message)
            }
        }
    }

    private fun resetViews() {
        with(binding) {
            pbSignUpLoading.visibility = View.GONE
            tilUsernameSignUpF.error = null
            tilFirstnameSignUpF.error = null
            tilLastnameSignUpF.error = null
            tilEmailSignUpF.error = null
            tilPhonenumberSignUpF.error = null
            tilPasswordSignUpF.error = null
            tilConfirmPasswordSignUpF.error = null
        }
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_signUp_to_Login)
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}
