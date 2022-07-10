package com.company.userservice.model

import javax.persistence.*

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(unique = true)
    val name: String
) {
    constructor(name: String) : this(
        null,
        name =name
    )
}
