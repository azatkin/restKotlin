package ru.ivmiit.service.forms

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
open class UserForm {
    val firstName: String? = null
    val lastName: String? = null
    val login: String? = null
    val email: String? = null
    val password: String? = null
    val confirmPassword: String? = null
}
