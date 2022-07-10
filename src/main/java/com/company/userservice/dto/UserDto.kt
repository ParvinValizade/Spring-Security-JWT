package com.company.userservice.dto

data class UserDto(
    val id: Long,
    val name: String,
    val username: String,
    val password: String,
    val roles: List<RoleDto>
)
