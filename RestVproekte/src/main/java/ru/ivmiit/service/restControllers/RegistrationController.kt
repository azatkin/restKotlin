package ru.ivmiit.service.restControllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.ivmiit.service.forms.UserForm
import ru.ivmiit.service.repositories.UsersRepository
import ru.ivmiit.service.services.UsersServiceImpl

@RestController
open class RegistrationController {
    @Autowired
    private val usersServiceImpl: UsersServiceImpl? = null

    @Autowired
    private val usersRepository: UsersRepository? = null

    @PostMapping("/registration")
    fun registration(@RequestBody userForm: UserForm): ResponseEntity<Map<String, Any?>> {
        val response: MutableMap<String, Any?> = HashMap()

        if (userForm.password != userForm.confirmPassword) {
            response["message"] = "Пароли не совпадают"
            return ResponseEntity(response, HttpStatus.BAD_REQUEST)
        }

        if (usersRepository!!.existsByLogin(userForm.login.toString())) {
            response["message"] = "Логин уже используется"
            return ResponseEntity(response, HttpStatus.BAD_REQUEST)
        }

        if (usersRepository.existsByEmail(userForm.email.toString())) {
            response["message"] = "Email уже используется"
            return ResponseEntity(response, HttpStatus.BAD_REQUEST)
        }

        try {
            usersServiceImpl!!.register(userForm)
            response["message"] = "Регистрация успешна"
            return ResponseEntity(response, HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            response["error"] = e.message
            return ResponseEntity(response, HttpStatus.BAD_REQUEST)
        }
    } //    @PostMapping("/registration")
    //    public ResponseEntity<UserForm> registration(@RequestBody UserForm userForm) {
    //        usersService.register(userForm);
    //        return ResponseEntity.ok().build();
    //    }
}
