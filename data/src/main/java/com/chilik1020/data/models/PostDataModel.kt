package com.chilik1020.data.models

data class PostDataModel(
    val id: Long,
    val closed: Boolean,
    val typeOfAnimal: String,
    val ageOfAnimal: String,
    val typeOfHelp: String,
    val description: String,
    val imageLink: String,
    val creatorUsername: String,
    val createdAt: String
)

data class PostsList(
    val posts: List<PostDataModel>
)