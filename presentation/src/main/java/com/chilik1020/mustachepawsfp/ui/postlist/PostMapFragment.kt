package com.chilik1020.mustachepawsfp.ui.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentPostMapBinding
import com.chilik1020.mustachepawsfp.models.PostPresentationModel
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
    }

    override fun onMapReady(gm: GoogleMap?) {
        googleMap = gm
        googleMap?.mapType = GoogleMap.MAP_TYPE_HYBRID
        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    53.90831018963494,
                    27.571897533128794
                ), 11f
            )
        )
    }

    fun render(state: PostsViewState) {
        when (state) {
            is PostsViewState.Loading -> {
            }
            is PostsViewState.Success -> {
                setMarkers(state.data)
            }
            is PostsViewState.Error -> {
                showSnackBarMessage(state.msg)
            }
        }
    }

    private fun initViews() {
        mapFragment.getMapAsync(this)
    }

    private fun setMarkers(post: List<PostPresentationModel>) {
        googleMap?.clear()
        post.forEach {
            val latLng = LatLng(it.location.lon, it.location.lat)
            val markerOptions = MarkerOptions()
            markerOptions.position(latLng)
            markerOptions.title(it.location.description)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
            googleMap?.addMarker(markerOptions)
        }
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}
