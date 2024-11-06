package ru.ivmiit.service.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.ivmiit.service.models.Request

interface RequestRepository : JpaRepository<Request?, Long?>
