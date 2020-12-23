package com.chilik1020.domain.models

data class PostRequestObject(
    val typeOfAnimal: String,
    val ageOfAnimal: String,
    val typeOfHelp: String,
    val description: String,
    val imageLink: String,
    val creatorUsername: String
)