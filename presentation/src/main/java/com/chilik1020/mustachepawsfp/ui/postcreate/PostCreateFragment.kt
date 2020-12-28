package com.chilik1020.mustachepawsfp.ui.postcreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.lifecycle.ViewModelProvider
import at.blogc.android.views.ExpandableTextView
import com.bumptech.glide.Glide
import com.chilik1020.mustachepawsfp.databinding.FragmentPostCreateBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostCreateFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: PostCreateViewModel
    lateinit var binding: FragmentPostCreateBinding

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
        viewModel.imageUri.observe(viewLifecycleOwner) {
            Glide.with(binding.root)
                .load(it)
                .into(binding.ivCapturedImage)
        }

        binding.etvPostDescription.setInterpolator(OvershootInterpolator())
        binding.etvPostDescription.setOnClickListener {
            it as ExpandableTextView
            if (it.isExpanded) {
                it.collapse()
            } else {
                it.expand()
            }
        }

        viewModel.typeOfHelp.observe(viewLifecycleOwner) { binding.tvTypeOfHelp.text = it }
        viewModel.location.observe(viewLifecycleOwner) { binding.tvPlace.text = it }
        viewModel.description.observe(viewLifecycleOwner) { binding.etvPostDescription.text = it }
        binding.btnPublishPost.setOnClickListener { viewModel.createPost() }
    }
}