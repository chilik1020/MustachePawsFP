package com.chilik1020.mustachepawsfp.models

import java.io.Serializable

class LocationPresentationModel(
    val description: String,
    val lat: Double,
    val lng: Double
) : Serializable