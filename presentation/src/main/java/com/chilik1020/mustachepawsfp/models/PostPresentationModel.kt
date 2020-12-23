package com.chilik1020.mustachepawsfp.models

data class PostPresentationModel(
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