package ru.ivmiit.service.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import ru.ivmiit.service.forms.UserForm
import ru.ivmiit.service.models.Role
import ru.ivmiit.service.models.State
import ru.ivmiit.service.models.User
import ru.ivmiit.service.repositories.UsersRepository
import java.util.*

@Service
open class UsersServiceImpl @Autowired constructor(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder
) : UsersService {

    override fun register(userForm: UserForm) {
        require(!usersRepository.existsByLogin(userForm.login.toString())) { "Логин уже занят" }
        require(!usersRepository.existsByEmail(userForm.email.toString())) { "Email уже занят" }

        val hashPassword = passwordEncoder.encode(userForm.password)

        val user = User(
            email = userForm.email,
            firstName = userForm.firstName,
            lastName = userForm.lastName,
            hashPassword = hashPassword,
            login = userForm.login,
            role = Role.USER,
            state = State.ACTIVE
        )

        usersRepository.save(user)
    }

    override fun findAll(): List<User> {
        println("findAll() called in UsersServiceImpl") // Временный лог
        return usersRepository.findAll()
    }

    override fun findOne(userId: Long): Optional<User?> {
        return usersRepository.findById(userId)
    }

    override fun update(userId: Long, userForm: UserForm): Boolean {
        // Реализуйте логику обновления пользователя
        return false
    }

    override fun delete(userId: Long): Boolean {
        // Реализуйте логику удаления пользователя
        return false
    }
}
