package com.chilik1020.mustachepawsfp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepawsfp.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_sign_up, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSignUp.setOnClickListener {
            viewModel.executeSignUp(
                tietUsernameSignUpF.text.toString(),
                tietEmailSignUpF.text.toString(),
                tietPasswordSignUpF.text.toString(),
                tietConfirmPasswordSignUpF.text.toString()
            )
        }

        viewModel.viewState.observe(this) { render(it) }
    }

    private fun render(state: SignUpViewState) {
        resetViews()
        when (state) {
            is SignUpViewState.SignUpLoadingState -> {
                pbSignUpLoading.visibility = View.VISIBLE
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
                tilUsernameSignUpF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is SignUpViewState.EmailErrorState -> {
                tilEmailSignUpF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is SignUpViewState.PasswordErrorState -> {
                tilPasswordSignUpF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is SignUpViewState.ConfirmPasswordErrorState -> {
                tilConfirmPasswordSignUpF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun resetViews() {
        pbSignUpLoading.visibility = View.GONE
        tilUsernameSignUpF.error = null
        tilEmailSignUpF.error = null
        tilPasswordSignUpF.error = null
        tilConfirmPasswordSignUpF.error = null
    }

    private fun navigateToPostListFragment() {

    }
}
