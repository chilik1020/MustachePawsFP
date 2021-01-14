package com.chilik1020.data.models

data class PostDataModel(
    val id: Long,
    val closed: Boolean,
    val animalType: String,
    val assistType: String,
    val location: PostLocation,
    val description: String,
    val imageLink: String,
    val creatorUsername: String,
    val createdAt: Long
)

data class ListPostDataModel(
    val data: List<PostDataModel>
)