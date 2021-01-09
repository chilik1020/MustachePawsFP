package com.chilik1020.mustachepawsfp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentProfileBinding
import com.chilik1020.mustachepawsfp.models.UserPresentationModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    private val userPostListAdapter = UserPostListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_toolbar_profile_frag, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.miEditMyProfileMenu -> {
                navigateToEditProfileFragment()
                true
            }

            R.id.miLogOutProfileMenu -> {
                navigateToLoginFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun initViews() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbarPostListFragment)
        }
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        viewModel.userDetailsViewState.observe(viewLifecycleOwner) { render(it) }

        binding.rvUserPostList.apply {
            adapter = userPostListAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        viewModel.userDetails()
    }

    private fun render(state: ProfileViewState) {
        when (state) {
            ProfileViewState.Loading -> {
                binding.pbUserDetailsLoading.visibility = View.VISIBLE
            }
            is ProfileViewState.Success -> {
                userPostListAdapter.setData(state.posts)
                bindUserDetails(state.user)
            }
            is ProfileViewState.Error -> {
                binding.pbUserDetailsLoading.visibility = View.GONE
                showSnackBarMessage(state.msg)
            }
        }
    }

    private fun bindUserDetails(user: UserPresentationModel) {
        with(binding) {
            pbUserDetailsLoading.visibility = View.GONE
            val fullname = "${user.firstname} ${user.lastname}"
            tvFullname.text = fullname
            tvUsername.text = user.username
            tvPhonenumber.text = user.phoneNumber
            tvEmail.text = user.email
            tvRegistered.text = user.createdAt
            Glide.with(root)
                .load(R.drawable.ic_default_avatar)
                .into(ivProfilePhoto)
        }
    }

    private fun navigateToEditProfileFragment() {
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_profile_to_Login)
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}