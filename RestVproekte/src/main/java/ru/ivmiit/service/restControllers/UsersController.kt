package ru.ivmiit.service.restControllers

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.ivmiit.service.forms.UserForm
import ru.ivmiit.service.models.User
import ru.ivmiit.service.services.UsersService
import java.util.*

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
open class UsersController(
    private val usersService: UsersService
) {

    @GetMapping
    fun getUsers(): List<User> = usersService.findAll()

    @GetMapping("/{user-id}")
    fun getUser(@PathVariable("user-id") userId: Long): ResponseEntity<User> {
        val user = usersService.findOne(userId)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun addUser(@RequestBody userForm: UserForm): ResponseEntity<Any> {
        usersService.register(userForm)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{user-id}")
    fun updateUser(@PathVariable("user-id") userId: Long, @RequestBody userForm: UserForm): ResponseEntity<Any> {
        val isUpdated = usersService.update(userId, userForm)
        return if (isUpdated) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{user-id}")
    fun deleteUser(@PathVariable("user-id") userId: Long): ResponseEntity<Any> {
        val isDeleted = usersService.delete(userId)
        return if (isDeleted) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
