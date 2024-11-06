package ru.ivmiit.service.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.ivmiit.service.models.Token
import java.util.*

interface TokensRepository : JpaRepository<Token?, Long?> {
    fun findOneByValue(value: String?): Optional<Token?>?
}
