package com.chilik1020.data.models

data class GoogleMapsLocationResponse(
    val status: String,
    val results: List<LocationResult>
)

data class LocationResult(
    val formattedAddress: String,
    val geometry: Geometry,
    val placeId: String
)

data class Geometry(
    val locationType: String,
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)