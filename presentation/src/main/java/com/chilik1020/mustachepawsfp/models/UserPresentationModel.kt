package com.chilik1020.mustachepawsfp.models

class UserPresentationModel(
    val username: String,
    val firstname: String?,
    val lastname: String?,
    val email: String?,
    val phoneNumber: String?,
    val profileImage: String?,
    val createdAt: Long
)