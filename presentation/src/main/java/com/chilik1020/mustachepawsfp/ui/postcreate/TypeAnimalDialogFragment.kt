package com.chilik1020.mustachepawsfp.ui.postcreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentDialogTypeAnimalBinding
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

class TypeAnimalDialogFragment : DaggerDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: PostCreateViewModel
    lateinit var binding: FragmentDialogTypeAnimalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogTypeAnimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostCreateViewModel::class.java)
        binding.ibGoToNextStep.setOnClickListener {
            viewModel.typeOfAnimal.value = "Turtle"
            navigateToNextStep()
        }
        binding.ibGoToPreviousStep.setOnClickListener { navigateToPreviousStep() }
    }

    private fun navigateToNextStep() {
        findNavController()
            .navigate(R.id.action_dialogTypeAnimal_to_createPost)
    }

    private fun navigateToPreviousStep() {
        findNavController().popBackStack()
    }
}