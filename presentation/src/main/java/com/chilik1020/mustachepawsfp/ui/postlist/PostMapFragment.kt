package com.chilik1020.mustachepawsfp.ui.postlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentPostMapBinding
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar

class PostMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentPostMapBinding
    private lateinit var mapFragment: SupportMapFragment
    private var googleMap: GoogleMap? = null
    private var currentLocation: LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostMapBinding.inflate(inflater, container, false)
        mapFragment = SupportMapFragment()
        childFragmentManager.beginTransaction()
            .add(R.id.mapView, mapFragment).commit()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        Log.d(LOG_TAG, "POSTMAPFRAGMENT onViewCreated")
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
    }

    fun render(state: PostsViewState) {
        Log.d(LOG_TAG, "POSTMAPFRAGMENT $state")
        when (state) {
            PostsViewState.Loading -> {
                showSnackBarMessage("Loading")
            }
            is PostsViewState.Success -> {
                showSnackBarMessage("Succees")
            }
            is PostsViewState.Error -> {
                showSnackBarMessage(state.msg)
            }
        }
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}
