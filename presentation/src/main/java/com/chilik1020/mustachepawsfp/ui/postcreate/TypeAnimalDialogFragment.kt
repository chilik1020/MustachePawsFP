package com.chilik1020.mustachepawsfp.ui.postcreate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentDialogTypeAnimalBinding
import com.chilik1020.mustachepawsfp.utils.EXTRA_KEY_ANIMAL_TYPE
import com.chilik1020.mustachepawsfp.utils.LOG_TAG

class TypeAnimalDialogFragment : DialogFragment() {

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
        binding.mbApply.setOnClickListener {
            val intent = Intent().apply {
                putExtra(EXTRA_KEY_ANIMAL_TYPE, getTextFromCheckedRadioButton())
            }
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            dismiss()
        }

        binding.mbCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun getTextFromCheckedRadioButton(): String {
        val checkedRB = binding.rgTypeAnimal.checkedRadioButtonId
        Log.d(LOG_TAG, "Checked $checkedRB")
        return when (checkedRB) {
            R.id.rbCat -> "Cat"
            R.id.rbDog -> "Dog"
            R.id.rbRabbit -> "Rabbit"
            R.id.rbMouse -> "Mouse"
            else -> binding.etOtherAnimal.text.toString()
        }
    }
}