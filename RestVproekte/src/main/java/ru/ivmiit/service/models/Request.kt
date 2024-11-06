package ru.ivmiit.service.models

import javax.persistence.*

@Entity
@Table(name = "request")
class Request(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var fio: String? = null,

    var phoneNumber: Long? = null,

    val request: String? = null
)
