package ru.ivmiit.service.services

import ru.ivmiit.service.forms.RequestForm
import ru.ivmiit.service.models.Request
import java.util.*

interface RequestService {
    fun createRequest(requestForm: RequestForm)
    fun findAll(): List<Request?>
    fun findById(id: Long): Optional<Request?>
    fun update(id: Long, requestForm: RequestForm): Boolean
    fun delete(id: Long): Boolean
}
