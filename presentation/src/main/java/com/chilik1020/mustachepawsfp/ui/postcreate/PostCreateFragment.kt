package com.chilik1020.mustachepawsfp.ui.postcreate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.chilik1020.mustachepawsfp.databinding.FragmentPostCreateBinding
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
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
        viewModel.typeOfHelp.observe(viewLifecycleOwner) { binding.tvTypeOfHelp.text = it }
        viewModel.location.observe(viewLifecycleOwner) { binding.tvPlace.text = it }
        binding.btnPublishPost.setOnClickListener { viewModel.createPost() }

        Log.d(LOG_TAG, viewModel.imageUri.value.toString())
        Log.d(LOG_TAG, viewModel.typeOfHelp.value.toString())
        Log.d(LOG_TAG, viewModel.typeOfAnimal.value.toString())
        Log.d(LOG_TAG, viewModel.location.value.toString())
    }
}