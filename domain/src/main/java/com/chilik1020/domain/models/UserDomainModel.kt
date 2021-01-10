package com.chilik1020.domain.models

class UserDomainModel(
    val id: Long,
    val username: String,
    val firstname: String?,
    val lastname: String?,
    val email: String?,
    val phoneNumber: String?,
    val profileImage: String?,
    val createdAt: String?
)