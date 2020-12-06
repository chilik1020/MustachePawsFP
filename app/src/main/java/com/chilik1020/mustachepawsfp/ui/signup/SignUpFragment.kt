package com.chilik1020.mustachepawsfp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepawsfp.databinding.FragmentSignUpBinding
import com.chilik1020.mustachepawsfp.di.ApplicationScope
import com.chilik1020.mustachepawsfp.di.ViewModelScope
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.lifecycle.closeOnDestroy
import toothpick.smoothie.viewmodel.closeOnViewModelCleared
import toothpick.smoothie.viewmodel.installViewModelBinding

class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectDependencies()
        initViews()
    }

    private fun injectDependencies() {
        KTP.openScopes(ApplicationScope::class.java)
            .openSubScope(ViewModelScope::class.java) {
                it.installViewModelBinding<SignUpViewModel>(this)
                    .closeOnViewModelCleared(this)
            }
            .closeOnDestroy(this)
            .inject(this)
    }

    private fun initViews() {
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
        viewModel.viewState.observe(viewLifecycleOwner) { render(it) }
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
