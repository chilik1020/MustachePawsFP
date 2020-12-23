package com.chilik1020.domain.models

data class PostRequestObject(
    val closed: Boolean,
    val description: String,
    val imageLink: String,
    val creatorUsername: String
)