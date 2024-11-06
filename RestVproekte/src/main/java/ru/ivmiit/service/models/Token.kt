package ru.ivmiit.service.models

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val value: String? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    val user: User? = null
)
