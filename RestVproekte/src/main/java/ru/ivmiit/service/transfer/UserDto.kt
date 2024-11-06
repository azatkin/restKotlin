package ru.ivmiit.service.transfer

import ru.ivmiit.service.models.User

data class UserDto(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email
            )
        }
    }
}
