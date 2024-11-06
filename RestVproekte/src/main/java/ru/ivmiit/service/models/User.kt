package ru.ivmiit.service.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val firstName: String? = null,
    val lastName: String? = null,
    val login: String? = null,
    val email: String? = null,
    val hashPassword: String? = null,

    @Enumerated(EnumType.STRING)
    val role: Role? = null,

    @Enumerated(EnumType.STRING)
    val state: State? = null,

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    var tokens: List<Token>? = null
)
