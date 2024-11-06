package ru.ivmiit.service.services

import ru.ivmiit.service.forms.UserForm
import ru.ivmiit.service.models.User
import java.util.*

interface UsersService {
    fun register(userForm: UserForm)

    fun findAll(): List<User>

    fun findOne(userId: Long): Optional<User?>

    fun update(userId: Long, userForm: UserForm): Boolean

    fun delete(userId: Long): Boolean
}
