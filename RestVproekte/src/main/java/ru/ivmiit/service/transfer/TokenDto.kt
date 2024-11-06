package ru.ivmiit.service.transfer

import ru.ivmiit.service.models.Token

data class TokenDto(
    val value: String? = null
) {
    companion object {
        fun from(token: Token): TokenDto {
            return TokenDto(token.value)
        }
    }
}
