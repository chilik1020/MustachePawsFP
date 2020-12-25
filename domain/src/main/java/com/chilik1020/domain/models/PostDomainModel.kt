package com.chilik1020.domain.models

data class PostDomainModel(
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