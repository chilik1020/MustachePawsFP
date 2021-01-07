package com.chilik1020.data.models

class MapQuestLocationResponse(
    val results: List<ResultMQ>
)

data class ResultMQ(
    val locations: List<LocationMQ>
)


data class LocationMQ(
    val latLng: LatLngMQ
)

data class LatLngMQ(
    val lat: Double,
    val lng: Double
)