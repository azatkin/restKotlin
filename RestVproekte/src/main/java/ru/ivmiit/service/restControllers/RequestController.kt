package ru.ivmiit.service.restControllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ivmiit.service.forms.RequestForm
import ru.ivmiit.service.services.RequestService

@RestController
@RequestMapping("/request")
open class RequestController {
    @Autowired
    private val requestService: RequestService? = null

    @Autowired
    private val mailSender: JavaMailSender? = null

    // Создание новой заявки (доступно всем)
    @PostMapping
    fun createRequest(@RequestBody requestForm: RequestForm): ResponseEntity<String> {
        // Сохранение заявки в базе данных
        requestService!!.createRequest(requestForm)

        // Формирование и отправка сообщения на email
        val message = SimpleMailMessage()
        message.setTo("nazat056@gmail.com")
        message.subject = "Новая заявка с сайта"
        message.text = """  
             ФИО: ${requestForm.fullName}
             Номер телефона: ${requestForm.phoneNumber}
             """.trimIndent()

        mailSender!!.send(message)

        return ResponseEntity("Ваша заявка успешно отправлена! Мы свяжемся с вами в ближайшее время.", HttpStatus.OK)
    }
}
