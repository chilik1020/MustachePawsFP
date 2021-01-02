package com.chilik1020.mustachepawsfp.ui.postlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chilik1020.mustachepawsfp.databinding.FragmentPostListBinding
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.google.android.material.snackbar.Snackbar

class PostListFragment : Fragment() {

    private lateinit var binding: FragmentPostListBinding
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
        Log.d(LOG_TAG, "POSTLISTFRAGMENT onViewCreated")
    }

    private fun initViews() {
        binding.rvPostList.apply {
            adapter = postListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    fun render(state: PostsViewState) {
        Log.d(LOG_TAG, "POSTLISTFRAGMENT $state")
        when (state) {
            PostsViewState.Loading -> {
                binding.pbFetchPostsLoading.visibility = View.VISIBLE
            }
            is PostsViewState.Success -> {
                binding.pbFetchPostsLoading.visibility = View.GONE
                postListAdapter.setData(state.data)
            }
            is PostsViewState.Error -> {
                binding.pbFetchPostsLoading.visibility = View.GONE
                showSnackBarMessage(state.msg)
            }
        }
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}