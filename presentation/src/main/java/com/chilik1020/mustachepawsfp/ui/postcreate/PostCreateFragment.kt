package com.chilik1020.mustachepawsfp.ui.postcreate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import at.blogc.android.views.ExpandableTextView
import com.bumptech.glide.Glide
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentPostCreateBinding
import com.chilik1020.mustachepawsfp.models.LocationPresentationModel
import com.chilik1020.mustachepawsfp.utils.EXTRA_KEY_ANIMAL_TYPE
import com.chilik1020.mustachepawsfp.utils.EXTRA_KEY_ASSIST_TYPE
import com.chilik1020.mustachepawsfp.utils.EXTRA_KEY_DESCRIPTION
import com.chilik1020.mustachepawsfp.utils.EXTRA_KEY_IMAGE_URI_PATH
import com.chilik1020.mustachepawsfp.utils.EXTRA_KEY_LOCATION
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.google.android.material.snackbar.Snackbar
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
        arguments?.let {
            val imageUri = it.getString(EXTRA_KEY_IMAGE_URI_PATH)
            Log.d(LOG_TAG, "ARGUMENT IMAGE URI $imageUri")
            viewModel.imageUri.value = imageUri
        }
        viewModel.imageUri.observe(viewLifecycleOwner) {
            Glide.with(binding.root)
                .load(it)
                .into(binding.ivCapturedImage)
        }
        viewModel.typeOfAnimal.observe(viewLifecycleOwner) { binding.tvTypeOfAnimal.text = it }
        viewModel.typeOfAssist.observe(viewLifecycleOwner) { binding.tvTypeOfAssist.text = it }
        viewModel.location.observe(viewLifecycleOwner) {
            binding.tvPlace.text = it.description
        }
        viewModel.description.observe(viewLifecycleOwner) { binding.etvPostDescription.text = it }
        viewModel.viewState.observe(viewLifecycleOwner) { render(it) }

        binding.etvPostDescription.setInterpolator(OvershootInterpolator())
        binding.etvPostDescription.setOnClickListener {
            it as ExpandableTextView
            if (it.isExpanded) {
                it.collapse()
            } else {
                it.expand()
            }
        }

        with(binding) {
            tvTypeOfAnimal.setOnClickListener { navigateToDialogAnimalType() }
            tvTypeOfAssist.setOnClickListener { navigateToDialogAssistType() }
            etvPostDescription.setOnClickListener { navigateToDialogDescription() }
            tvPlace.setOnClickListener { navigateToSelectLocation() }
            btnPublishPost.setOnClickListener { viewModel.createPost() }
        }
    }

    private fun navigateToDialogAssistType() {
        val assistanceTypeDialog = TypeAssistanceDialogFragment()
            .apply { setTargetFragment(this@PostCreateFragment, REQUEST_TYPE_ASSISTANCE) }
        assistanceTypeDialog.show(parentFragmentManager, assistanceTypeDialog.javaClass.name)
    }

    private fun navigateToDialogAnimalType() {
        val animalTypeDialog = TypeAnimalDialogFragment()
            .apply { setTargetFragment(this@PostCreateFragment, REQUEST_TYPE_ANIMAL) }
        animalTypeDialog.show(parentFragmentManager, animalTypeDialog.javaClass.name)
    }

    private fun navigateToSelectLocation() {
        val selectLocationDialog = SelectLocationDialogFragment()
            .apply { setTargetFragment(this@PostCreateFragment, REQUEST_LOCATION) }
        selectLocationDialog.show(parentFragmentManager, selectLocationDialog.javaClass.name)
    }

    private fun navigateToDialogDescription() {
        val descriptionDialogFragment = PostDescriptionDialogFragment()
            .apply { setTargetFragment(this@PostCreateFragment, REQUEST_DESCRIPTION) }
        descriptionDialogFragment.show(
            parentFragmentManager,
            descriptionDialogFragment.javaClass.name
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_DESCRIPTION -> {
                    data?.getStringExtra(EXTRA_KEY_DESCRIPTION).let {
                        viewModel.description.value = it
                    }
                }

                REQUEST_TYPE_ASSISTANCE -> {
                    data?.getStringExtra(EXTRA_KEY_ASSIST_TYPE).let {
                        viewModel.typeOfAssist.value = it
                    }
                }

                REQUEST_TYPE_ANIMAL -> {
                    data?.getStringExtra(EXTRA_KEY_ANIMAL_TYPE).let {
                        viewModel.typeOfAnimal.value = it
                    }
                }

                REQUEST_LOCATION -> {
                    data?.getSerializableExtra(EXTRA_KEY_LOCATION).let {
                        viewModel.location.value = it as LocationPresentationModel
                    }
                }

                else -> {
                    Log.d(LOG_TAG, "UNKNOWN REQUEST CODE")
                }
            }
        }
    }

    private fun render(state: PostCreateViewState) {
        when (state) {
            PostCreateViewState.Loading -> {
                binding.btnPublishPost.isActivated = false
            }
            is PostCreateViewState.Success -> {
                navigateToPostList()
            }
            is PostCreateViewState.Error -> {
                binding.btnPublishPost.isActivated = true
                Snackbar.make(binding.root, state.msg.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToPostList() {
        findNavController().navigate(R.id.action_createPost_to_PostList)
    }

    companion object {
        const val REQUEST_TYPE_ASSISTANCE = 4202
        const val REQUEST_TYPE_ANIMAL = 4203
        const val REQUEST_LOCATION = 4204
        const val REQUEST_DESCRIPTION = 4205
    }
}