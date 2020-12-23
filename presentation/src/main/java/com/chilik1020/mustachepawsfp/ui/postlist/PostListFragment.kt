package com.chilik1020.mustachepawsfp.ui.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.chilik1020.mustachepawsfp.databinding.FragmentPostListBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentPostListBinding
    lateinit var viewModel: PostListViewModel

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
        viewModel.viewState.observe(viewLifecycleOwner) { render(it) }
    }

    private fun render(state: PostListViewState) {
        when (state) {
            PostListViewState.Loading -> TODO()
            is PostListViewState.Success -> TODO()
            is PostListViewState.Error -> TODO()
        }
    }
}