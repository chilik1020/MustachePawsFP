package com.chilik1020.mustachepawsfp.ui.profileedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentEditProfileBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EditProfileFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: EditProfileViewModel
    lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)
        viewModel.getCurrentProfile()
        viewModel.currentProfileLD.observe(viewLifecycleOwner) {
            with(binding) {
                tietFirstnameEditF.setText(it.firstname)
                tietLastnameEditF.setText(it.lastname)
                tietEmailEditF.setText(it.email)
                tietPhonenumberEditF.setText(it.phoneNumber)
            }
        }

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbaEditProfileFragment)
            (activity as AppCompatActivity).supportActionBar?.apply {
                setDisplayShowHomeEnabled(true)
                setDisplayHomeAsUpEnabled(true)
            }
        }

        binding.toolbaEditProfileFragment.setNavigationOnClickListener { findNavController().popBackStack() }

        Glide.with(binding.root)
            .load(R.drawable.default_user_avatar)
            .circleCrop()
            .into(binding.ivProfilePhoto)
    }
}