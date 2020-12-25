package com.chilik1020.mustachepawsfp.ui.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentPostListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentPostListBinding
    lateinit var viewModel: PostListViewModel
    private val postListAdapter = PostListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostListViewModel::class.java)

        binding.rvPostList.apply {
            adapter = postListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        binding.ivGoToProfileFragment.setOnClickListener { navigateToProfileFragment() }

        binding.ivReFetchPosts.setOnClickListener {
            viewModel.fetchPosts()
        }

        viewModel.viewState.observe(viewLifecycleOwner) { render(it) }
        viewModel.fetchPosts()
    }

    private fun render(state: PostListViewState) {
        when (state) {
            PostListViewState.Loading -> {
                binding.pbFetchPostsLoading.visibility = View.VISIBLE
            }
            is PostListViewState.Success -> {
                binding.pbFetchPostsLoading.visibility = View.GONE
                postListAdapter.setData(state.data)
            }
            is PostListViewState.Error -> {
                binding.pbFetchPostsLoading.visibility = View.GONE
                showSnackBarMessage(state.msg)
            }
        }
    }

    private fun navigateToProfileFragment() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_postListFragment_to_profileFragment)
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}