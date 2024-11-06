package ru.ivmiit.service.forms

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class RequestForm {
    val fullName: String? = null
    val phoneNumber: Long? = null
}
