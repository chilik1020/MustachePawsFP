package com.chilik1020.mustachepawsfp.model.entities

data class UserRequestObject(
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phonenumber: String,
    val password: String
)