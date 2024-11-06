package ru.ivmiit.service.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ivmiit.service.forms.RequestForm
import ru.ivmiit.service.models.Request
import ru.ivmiit.service.repositories.RequestRepository
import java.util.*

@Service
class RequestServiceImpl @Autowired constructor(
    private val requestRepository: RequestRepository
) : RequestService {

    override fun createRequest(requestForm: RequestForm) {
        val request = Request(
            fio = requestForm.fullName,
            phoneNumber = requestForm.phoneNumber
        )
        requestRepository.save(request)
    }

    override fun findAll(): MutableList<Request?> {
        return requestRepository.findAll()
    }

    override fun findById(id: Long): Optional<Request?> {
        return requestRepository.findById(id)
    }

    override fun update(id: Long, requestForm: RequestForm): Boolean {
        val existingRequest = requestRepository.findById(id)
        return if (existingRequest.isPresent) {
            val request = existingRequest.get().apply {
                fio = requestForm.fullName
                phoneNumber = requestForm.phoneNumber
            }
            requestRepository.save(request)
            true
        } else {
            false
        }
    }

    override fun delete(id: Long): Boolean {
        return if (requestRepository.existsById(id)) {
            requestRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}
