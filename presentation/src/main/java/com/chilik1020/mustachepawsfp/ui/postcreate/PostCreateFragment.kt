package com.chilik1020.mustachepawsfp.ui.postcreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.chilik1020.mustachepawsfp.databinding.FragmentPostCreateBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostCreateFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentPostCreateBinding
    lateinit var viewModel: PostCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostCreateViewModel::class.java)
    }
}