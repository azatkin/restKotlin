package ru.ivmiit.service.restControllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.ivmiit.service.forms.RequestForm
import ru.ivmiit.service.models.Request
import ru.ivmiit.service.services.RequestService

@RestController
@RequestMapping("/admin/requests")
@PreAuthorize("hasAuthority('ADMIN')") // Ограничение доступа для всего контроллера
open class AdminRequestController {
    @Autowired
    private val requestService: RequestService? = null

    @get:GetMapping
    val allRequests: ResponseEntity<List<Request?>?>
        // Получение всех заявок (доступно только администратору)
        get() {
            val requests = requestService!!.findAll()
            return ResponseEntity.ok(requests)
        }


    // Обновление заявки по ID (доступно только администратору)
    @PutMapping("/{id}")
    fun updateRequest(@PathVariable id: Long, @RequestBody requestForm: RequestForm): ResponseEntity<String> {
        val isUpdated = requestService!!.update(id, requestForm)
        return if (isUpdated) {
            ResponseEntity.ok("Заявка успешно обновлена")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заявка не найдена")
        }
    }

    // Удаление заявки по ID (доступно только администратору)
    @DeleteMapping("/{id}")
    fun deleteRequest(@PathVariable id: Long): ResponseEntity<String> {
        val isDeleted = requestService!!.delete(id)
        return if (isDeleted) {
            ResponseEntity.ok("Заявка успешно удалена")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заявка не найдена")
        }
    }
}
