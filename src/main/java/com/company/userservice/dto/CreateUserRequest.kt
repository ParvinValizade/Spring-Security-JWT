package com.company.userservice.dto

data class CreateUserRequest(
    val name: String,
    val username: String,
    val password: String,
    val roles: List<String>
)
