package ru.ivmiit.service.restControllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.ivmiit.service.repositories.UsersRepository
import ru.ivmiit.service.transfer.UserDto

@RestController
open class ProfileController {
    @Autowired
    private val usersRepository: UsersRepository? = null

    @GetMapping("/profile")
    fun userInfo(authentication: Authentication): ResponseEntity<*> {
        try {
            if (authentication == null || !authentication.isAuthenticated) {
                throw RuntimeException("User not authenticated")
            }

            val username = authentication.name
            println("Authenticated user: $username") // Добавлено для отладки

            val user = usersRepository!!.findByLogin(username)
                .orElseThrow { RuntimeException("User not found") }

            val userDto: UserDto = UserDto.Companion.from(user)
            return ResponseEntity.ok(userDto)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
        }
    }
}
