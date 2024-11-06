package ru.ivmiit.service.services

import org.springframework.stereotype.Service
import ru.ivmiit.service.forms.LoginForm
import ru.ivmiit.service.transfer.TokenDto

@Service
interface LoginService {
    fun login(loginForm: LoginForm): TokenDto
}
