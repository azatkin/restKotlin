package ru.ivmiit.service.forms

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class LoginForm {
    val login: String? = null
    val password: String? = null
}
