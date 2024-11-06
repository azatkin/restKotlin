package ru.ivmiit.service.services

import org.apache.commons.lang.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import ru.ivmiit.service.forms.LoginForm
import ru.ivmiit.service.models.Token
import ru.ivmiit.service.repositories.TokensRepository
import ru.ivmiit.service.repositories.UsersRepository
import ru.ivmiit.service.transfer.TokenDto

@Component
class LoginServiceImpl @Autowired constructor(
    private val tokensRepository: TokensRepository,
    private val passwordEncoder: PasswordEncoder,
    private val usersRepository: UsersRepository
) : LoginService {

    override fun login(loginForm: LoginForm): TokenDto {
        val userCandidate = usersRepository.findByLogin(loginForm.login.toString())

        if (userCandidate.isPresent) {
            val user = userCandidate.get()

            if (passwordEncoder.matches(loginForm.password, user.hashPassword)) {
                val token = Token(
                    user = user,
                    value = RandomStringUtils.random(10, true, true)
                )
                tokensRepository.save(token)
                return TokenDto.from(token)
            }
        }
        throw IllegalArgumentException("User not found")
    }
}
