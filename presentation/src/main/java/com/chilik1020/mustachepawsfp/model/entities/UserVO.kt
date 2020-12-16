package com.chilik1020.mustachepawsfp.model.entities

data class UserVO(
    val id: Long,
    val username: String?,
    val firstname: String?,
    val lastname: String?,
    val email: String?,
    val phoneNumber: String?,
    val createdAt: String?
)