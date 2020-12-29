package com.chilik1020.mustachepawsfp.ui.postcreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentDialogSelectLocationBinding
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject


class SelectLocationDialogFragment : DaggerDialogFragment(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: PostCreateViewModel
    lateinit var binding: FragmentDialogSelectLocationBinding

    private lateinit var mapFragment: SupportMapFragment
    private var googleMap: GoogleMap? = null
    private var currentLocation: LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogSelectLocationBinding.inflate(inflater, container, false)
        mapFragment = SupportMapFragment()
        childFragmentManager.beginTransaction()
            .add(R.id.mapView, mapFragment).commit()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onMapReady(gm: GoogleMap?) {
        googleMap = gm
        googleMap?.let {
            it.mapType = GoogleMap.MAP_TYPE_HYBRID
            currentLocation = LatLng(53.90831018963494, 27.571897533128794)
            currentLocation?.let { currentLatLng ->
                val latLng: LatLng = currentLatLng
                it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11f))

                val markerOptions = MarkerOptions()
                markerOptions.position(latLng)
                markerOptions.title("Current Position")
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                it.addMarker(markerOptions)
            }
        }
    }

    private fun initViews() {
        mapFragment.getMapAsync(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(PostCreateViewModel::class.java)

        viewModel.location.observe(viewLifecycleOwner) { currentLocation = it }
        binding.ibGoToPreviousStep.setOnClickListener { navigateToPreviousStep() }
        binding.ibGoToNextStep.setOnClickListener {
            viewModel.location.value = currentLocation
            navigateToNextStep()
        }
    }

    private fun navigateToNextStep() {
        findNavController()
            .navigate(R.id.action_selectLocation_to_createPost)
    }

    private fun navigateToPreviousStep() {
        findNavController().popBackStack()
    }
}