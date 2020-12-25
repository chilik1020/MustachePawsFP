package com.chilik1020.mustachepawsfp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        viewModel.viewState.observe(viewLifecycleOwner) { render(it) }
        viewModel.userDetails()
    }

    private fun render(state: ProfileViewState) {
        when (state) {
            ProfileViewState.Loading -> {
                binding.pbUserDetailsLoading.visibility = View.VISIBLE
            }
            is ProfileViewState.Success -> {
                with(binding) {
                    pbUserDetailsLoading.visibility = View.GONE
                    val fullname = "${state.data.firstname} + ${state.data.lastname}"
                    tvFullname.text = fullname
                    tvUsername.text = state.data.username
                    tvPhonenumber.text = state.data.phoneNumber
                    tvEmail.text = state.data.email
                    tvRegistered.text = "Registered at 24/12/2020"
                    Glide.with(root)
                        .load(R.drawable.ic_logo)
                        .into(ivProfilePhoto)
                }
            }
            is ProfileViewState.Error -> {
                binding.pbUserDetailsLoading.visibility = View.GONE
                showSnackBarMessage(state.msg)
            }
        }
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}