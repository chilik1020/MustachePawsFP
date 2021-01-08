package com.chilik1020.mustachepawsfp.models

import com.chilik1020.domain.models.PostLocation


data class PostPresentationModel(
    val id: Long,
    val closed: Boolean,
    val typeOfAnimal: String,
    val typeOfAssist: String,
    val location: PostLocation,
    val description: String,
    val imageLink: String,
    val creatorUsername: String,
    val createdAt: Long
)