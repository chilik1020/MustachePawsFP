package com.chilik1020.mustachepawsfp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepawsfp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(binding) {
            btnSignUp.setOnClickListener {
                viewModel.executeSignUp(
                    tietUsernameSignUpF.text.toString(),
                    tietEmailSignUpF.text.toString(),
                    tietPasswordSignUpF.text.toString(),
                    tietConfirmPasswordSignUpF.text.toString()
                )
            }

        }

        viewModel.viewState.observe(this) { render(it) }
    }

    private fun render(state: SignUpViewState) {
        resetViews()
        when (state) {
            is SignUpViewState.SignUpLoadingState -> {
                binding.pbSignUpLoading.visibility = View.VISIBLE
            }

            is SignUpViewState.SignUpFinishedState -> {
                Toast.makeText(activity, "You have successfully registered!", Toast.LENGTH_LONG)
                    .show()
                navigateToPostListFragment()
            }

            is SignUpViewState.SignUpErrorState -> {
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is SignUpViewState.UsernameErrorState -> {
                binding.tilUsernameSignUpF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is SignUpViewState.EmailErrorState -> {
                binding.tilEmailSignUpF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is SignUpViewState.PasswordErrorState -> {
                binding.tilPasswordSignUpF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is SignUpViewState.ConfirmPasswordErrorState -> {
                binding.tilConfirmPasswordSignUpF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun resetViews() {
        with(binding) {
            pbSignUpLoading.visibility = View.GONE
            tilUsernameSignUpF.error = null
            tilEmailSignUpF.error = null
            tilPasswordSignUpF.error = null
            tilConfirmPasswordSignUpF.error = null
        }
    }

    private fun navigateToPostListFragment() {

    }
}
