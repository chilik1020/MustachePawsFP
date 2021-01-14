package com.chilik1020.domain.models

data class PostRequestObject(
    val animalType: String,
    val assistType: String,
    val description: String,
    val location: PostLocation,
    val imageLink: String,
    val creatorUsername: String
)

data class PostLocation(
    val lat: Double,
    val lon: Double,
    val description: String
)