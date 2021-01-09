package com.chilik1020.data.models

class MapQuestLocationResponse(
    val results: List<ResultMQ>
)

data class ResultMQ(
    val locations: List<LocationMQ>
)

data class LocationMQ(
    val adminArea5: String,
    val adminArea6: String,
    val latLng: LatLngMQ
)

data class LatLngMQ(
    val lat: Double,
    val lng: Double
)