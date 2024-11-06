package ru.ivmiit.service.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.ivmiit.service.models.User
import java.util.Optional

interface UsersRepository : JpaRepository<User, Long> {
    fun findByLogin(login: String): Optional<User>

    fun existsByLogin(login: String): Boolean

    fun existsByEmail(email: String): Boolean
}
