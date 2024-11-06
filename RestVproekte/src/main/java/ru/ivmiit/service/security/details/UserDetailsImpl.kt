package ru.ivmiit.service.security.details

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.ivmiit.service.models.State
import ru.ivmiit.service.models.User

class UserDetailsImpl(val user: User) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority(user.role?.name ?: "DEFAULT_ROLE"))
    }


    override fun getPassword(): String? {
        return user.hashPassword
    }

    override fun getUsername(): String? {
        return user.login
    }

    override fun isAccountNonExpired(): Boolean {
        return user.state != State.BANNED
    }

    override fun isAccountNonLocked(): Boolean {
        return user.state != State.BANNED
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return user.state == State.ACTIVE
    }
}
