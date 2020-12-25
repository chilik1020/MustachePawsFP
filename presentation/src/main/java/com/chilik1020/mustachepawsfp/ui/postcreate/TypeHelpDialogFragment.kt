package com.chilik1020.mustachepawsfp.ui.postcreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentDialogTypeHelpBinding
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

class TypeHelpDialogFragment : DaggerDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: PostCreateViewModel
    lateinit var binding: FragmentDialogTypeHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogTypeHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostCreateViewModel::class.java)
        binding.ibGoToNextStep.setOnClickListener {
            viewModel.typeOfHelp.value = "Assistance 1"
            viewModel.location.value = "Barcelona, Spain"
            navigateToNextStep() }
        binding.ibGoToPreviousStep.setOnClickListener { navigateToPreviousStep() }
//
//        Navigation.findNavController(binding.root).backStack.forEach {
//            Log.d(LOG_TAG, "${it.destination}")
//        }
    }

    private fun navigateToNextStep() {
        findNavController()
            .navigate(R.id.action_dialogTypeHelp_to_dialogTypeAnimal)
    }

    private fun navigateToPreviousStep() {
        findNavController().popBackStack()
    }
}