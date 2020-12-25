package com.chilik1020.data.models

data class PostDataModel(
    val id: Long,
    val closed: Boolean,
    val typeOfAnimal: String = "cat",
    val ageOfAnimal: String = "12",
    val typeOfHelp: String = "feed",
    val description: String,
    val imageLink: String,
    val creatorUsername: String,
    val createdAt: String
)

data class ListPostDataModel(
    val data: List<PostDataModel>
)