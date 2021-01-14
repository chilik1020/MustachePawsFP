package com.chilik1020.mustachepawsfp.ui.postcreate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.chilik1020.mustachepawsfp.databinding.FragmentDialogDescriptionBinding
import com.chilik1020.mustachepawsfp.utils.EXTRA_KEY_DESCRIPTION

class PostDescriptionDialogFragment : DialogFragment() {
    lateinit var binding: FragmentDialogDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.mbApply.setOnClickListener {
            val intent = Intent().apply {
                putExtra(EXTRA_KEY_DESCRIPTION, binding.etDescription.text.toString())
            }
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            dismiss()
        }

        binding.mbCancel.setOnClickListener {
            dismiss()
        }
    }
}