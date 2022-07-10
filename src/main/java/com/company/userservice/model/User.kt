package com.company.userservice.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    val name: String?,
    val username: String?,
    var password: String?,

    @ManyToMany( fetch = FetchType.EAGER)
    val roles: List<Role>?
) {
    constructor(Name: String, Username: String, Password: String, Roles: List<Role>) : this(
        null,
        name =Name,
        username = Username,
        password = Password,
        roles = Roles
    )


}
