package com.chilik1020.mustachepawsfp.ui.postcreate

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chilik1020.mustachepawsfp.BuildConfig
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentImageCaptureBinding
import com.chilik1020.mustachepawsfp.utils.EXTRA_KEY_IMAGE_URI_PATH
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.chilik1020.mustachepawsfp.utils.createImageFile
import com.yalantis.ucrop.UCrop
import java.io.File

class ImageCaptureFragment : Fragment() {
    lateinit var binding: FragmentImageCaptureBinding

    lateinit var photoOriginalFile: File
    lateinit var photoCroppedFile: File
    lateinit var photoOriginalUri: Uri
    lateinit var photoCroppedUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageCaptureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        onClickImageCapture()
    }

    private fun onClickImageCapture() {
        createImageFiles()
        startImageCapture()
    }

    private fun createImageFiles() {
        binding.ivCapturedImage.setImageResource(android.R.color.transparent)

        photoOriginalFile = createImageFile(requireContext(), "original")
        photoCroppedFile = createImageFile(requireContext(), "cropped")

        if (!photoOriginalFile.exists() || !photoCroppedFile.exists())
            return

        photoCroppedUri = Uri.fromFile(photoCroppedFile)
        photoOriginalUri = FileProvider.getUriForFile(
            requireContext(),
            "${BuildConfig.APPLICATION_ID}.provider",
            photoOriginalFile
        )

        Log.d(LOG_TAG, "ORIGINAL PATH: ${photoOriginalFile.absolutePath}")
        Log.d(LOG_TAG, "CROPPED PATH: ${photoCroppedFile.absolutePath}")
        Log.d(LOG_TAG, "ORIGINAL URI: $photoOriginalUri")
        Log.d(LOG_TAG, "CROPPED URI: $photoCroppedUri")
    }

    private fun startImageCapture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoOriginalUri)
        }
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun startUCropActivity() {
        UCrop.of(photoOriginalUri, photoCroppedUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1080, 1080)
            .start(requireActivity(), this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
//                    Glide.with(this).load(photoOriginalUri).into(binding.ivCapturedImage)
                    startUCropActivity()
                }

                UCrop.REQUEST_CROP -> {
//                    Glide.with(this).load(photoCroppedUri).into(binding.ivCapturedImage)
                    navigateToPostCreate()
                }
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val throwable = data?.let { UCrop.getError(it) }
            Log.d(LOG_TAG, throwable?.message.toString())
        }
    }

    private fun navigateToPostCreate() {
        val bundle = Bundle().apply {
            putString(EXTRA_KEY_IMAGE_URI_PATH, photoCroppedUri.path)
        }
        findNavController().navigate(R.id.action_imageCapture_to_PostCreate, bundle)
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 4201
    }
}