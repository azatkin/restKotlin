package ru.ivmiit.service.restControllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.ivmiit.service.forms.LoginForm
import ru.ivmiit.service.services.LoginService
import ru.ivmiit.service.transfer.TokenDto

@RestController
open class LoginController {
    @Autowired
    private val loginService: LoginService? = null

    @PostMapping("/login")
    fun login(@RequestBody loginForm: LoginForm): ResponseEntity<TokenDto?> {
        return ResponseEntity.ok(loginService!!.login(loginForm))
    }
}
